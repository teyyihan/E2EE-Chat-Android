package com.teyyihan.core.util

import android.util.Log
import com.teyyihan.data.local.abstraction.FriendLocalDataSource
import com.teyyihan.data.model.entity.Friend
import com.teyyihan.data.model.response.FriendResponse
import com.teyyihan.domain.friend.util.SessionManager

private val TAG = "teooo FriendUtil"

suspend fun insertFriendIntoDB(
    friendResponse: FriendResponse,
    sessionManager: SessionManager,
    friendLocalDataSource: FriendLocalDataSource
): Friend? {
    val myPrivateKey = sessionManager.getUserCache()?.privateKey
    val friendPublicKey = friendResponse.publicKey.convertToPublicKey()
    val friendSharedKey = KeyUtil.generateSharedSecret(myPrivateKey, friendPublicKey)

    if (friendSharedKey != null && friendPublicKey != null) {
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