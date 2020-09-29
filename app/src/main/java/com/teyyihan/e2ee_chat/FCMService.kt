package com.teyyihan.e2ee_chat

import android.annotation.SuppressLint
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.teyyihan.core.util.KeyUtil
import com.teyyihan.domain.friend.util.SessionManager
import com.teyyihan.core.util.convertToPublicKey
import com.teyyihan.domain.friend.util.safeApiCall
import com.teyyihan.data.local.abstraction.FriendLocalDataSource
import com.teyyihan.data.local.abstraction.MessageLocalDataSource
import com.teyyihan.data.model.entity.Friend
import com.teyyihan.data.model.entity.Message
import com.teyyihan.data.model.response.FriendResponse
import com.teyyihan.data.remote.abstraction.ResourceRemoteDataSource
import com.teyyihan.data.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
@AndroidEntryPoint
class FCMService : FirebaseMessagingService() {

    private val TAG = "teooo FCMService"

    @Inject
    lateinit var friendLocalDataSource: FriendLocalDataSource

    @Inject
    lateinit var messageLocalDataSource: MessageLocalDataSource

    @Inject
    lateinit var friendRemoteDataSource: ResourceRemoteDataSource

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onMessageReceived(p0: RemoteMessage?) {
        super.onMessageReceived(p0)

        runBlocking {
            val messageModel = getMessageModel(p0?.data)
            if (messageModel != null) {

                Log.d(TAG, "onMessageReceived: $messageModel")
                
                val friendStatus = checkFriend(messageModel)
                if (friendStatus != null) {
                    // Cached friend
                    decryptAndInsertMessage(messageModel, friendStatus)
                    // Inserted message
                } else {
                    val friendResponse = getFriendFromServer(messageModel.fromUsername)
                    if (friendResponse is Resource.Success) {
                        friendResponse.value?.let {
                            val insertedFriend = insertFriendIntoDB(it)
                            insertedFriend?.let { gotFriend ->
                                // Got friend
                                decryptAndInsertMessage(messageModel, gotFriend)
                                // Inserted message
                            }
                        }
                    } else if (friendResponse is Resource.GenericError) {
                        Log.d(
                            TAG,
                            "onMessageReceived: Couldn't get friend from server ${friendResponse.errorMessage}  ${friendResponse.exception?.localizedMessage}"
                        )
                    }
                }
            }
        }

    }

    private suspend fun decryptAndInsertMessage(messageModel: FCMMessageModel, friend: Friend) {
        val cipherBytes = extractMessageBytes(messageModel.cipher)
        cipherBytes?.let {
            val decryptedMessage = KeyUtil.decryptMessage(cipherBytes, friend.sharedSecretKey)
            decryptedMessage?.let {
                val dateMillis = try {
                    messageModel.date.toLong()
                }catch (e: Exception){
                    null
                }
                dateMillis?.let {
                    val message = Message(
                        friendUsername = friend.friendUsername,
                        body = decryptedMessage,
                        date = dateMillis,
                        byMe = false
                    )
                    Log.d(TAG, "decryptAndInsertMessage: decrypted message $decryptedMessage")
                    messageLocalDataSource.insertMessage(message)
                }
            }
        }

    }

    private suspend fun insertFriendIntoDB(friendResponse: FriendResponse): Friend? {
        val myPrivateKey = sessionManager.getUserCache()?.privateKey
        val friendPublicKey = friendResponse.publicKey.convertToPublicKey()
        val friendSharedKey = KeyUtil.generateSharedSecret(myPrivateKey,friendPublicKey)

        if(friendSharedKey != null && friendPublicKey != null){
            val friend = Friend(
                friendResponse.username,
                friendPublicKey,
                friendSharedKey
            )

            val inserted = friendLocalDataSource.insertFriend(friend)
            Log.d(TAG, "insertFriendIntoDB: inserted friend if 1 then successful => $inserted")
            return friend
        }
        return null
    }

    private suspend fun getFriendFromServer(friendUsername: String): Resource<FriendResponse?> =
        safeApiCall(sessionManager) {
            friendRemoteDataSource.getFriend(it, friendUsername)
        }


    private suspend fun checkFriend(messageModel: FCMMessageModel): Friend? =
        friendLocalDataSource.getFriend(messageModel.fromUsername)


    private fun getMessageModel(data: Map<String, String>?): FCMMessageModel? {
        try {
            val fromUserName = data?.get("fromUsername")
            val cipherText = data?.get("cipherText")
            val date = System.currentTimeMillis()
            if (fromUserName != null && cipherText != null) {
                return FCMMessageModel(
                    fromUserName,
                    cipherText,
                    date
                )
            }
            return null
        } catch (e: Exception) {
            Log.d(TAG, "getMessageModel: ${e.localizedMessage}")
            return null
        }
    }

}

private fun extractMessageBytes(cipher: String?): ByteArray? {
    return try {
        cipher?.trimStart('[')?.trimEnd(']')?.split(",")?.map {
            it.toInt().toByte()
        }?.toByteArray()
    } catch (e: Exception) {
        null
    }
}

data class FCMMessageModel(
    val fromUsername: String,
    val cipher: String,
    val date: Long
)
