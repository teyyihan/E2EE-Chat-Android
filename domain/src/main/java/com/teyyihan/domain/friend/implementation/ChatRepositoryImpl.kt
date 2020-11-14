package com.teyyihan.domain.friend.implementation


import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.teyyihan.data.local.abstraction.MessageLocalDataSource
import com.teyyihan.data.model.UserLocal
import com.teyyihan.data.model.entity.FriendRepresentation
import com.teyyihan.data.model.entity.Message
import com.teyyihan.data.model.request.MessageRequest
import com.teyyihan.data.remote.abstraction.ResourceRemoteDataSource
import com.teyyihan.data.util.KeyUtil
import com.teyyihan.data.util.Resource
import com.teyyihan.domain.friend.abstraction.ChatRepository
import com.teyyihan.domain.friend.util.SessionManager
import com.teyyihan.domain.friend.util.safeApiCall
import kotlinx.coroutines.flow.Flow
import javax.crypto.Cipher

class ChatRepositoryImpl(
    private val messageLocalDataSource: MessageLocalDataSource,
    private val resourceRemoteDataSource: ResourceRemoteDataSource,
    private val sessionManager: SessionManager
) : ChatRepository {

    private val TAG = "teooo ChatRepository"

    override suspend fun sendMessage(
        text: String,
        friend: FriendRepresentation,
        userLocal: UserLocal
    ): Resource<Unit?> {

        // Insert into db
        messageLocalDataSource.insertMessage(
            Message(
                friendUsername = friend.friendUsername,
                body = text,
                date = System.currentTimeMillis(),
                byMe = true
            )
        )

        val oneTimeKeyPair = KeyUtil.generateKeypairAsCustomer(friend.friendPublicKey)
        val oneTimeAgreement = KeyUtil.keyAgreement(oneTimeKeyPair.private,friend.friendPublicKey)
        val oneTimeAesKey = KeyUtil.generateSharedAESKey(oneTimeAgreement)
        val oneTimeIV = KeyUtil.getRandomNonce()

        val encryptedText = KeyUtil.encryptOrDecrypt(text = text.toByteArray(),oneTimeAesKey,Cipher.ENCRYPT_MODE,oneTimeIV)

        // Send to remote server
        return safeApiCall(sessionManager) {
            resourceRemoteDataSource.sendMessage(
                it,
                MessageRequest(
                    toUsername = friend.friendUsername,
                    fromUsername = userLocal.username,
                    cipherText = encryptedText,
                    senderPublicKey = oneTimeKeyPair.public,
                    messageSpecificIV = oneTimeIV
                )
            )
        }

    }

    override fun getMessagesWithFriend(friendUsername: String): LiveData<PagedList<Message>> {
        return messageLocalDataSource.getMessagesWithFriend(friendUsername).toLiveData(pageSize = 20)
    }

}