package com.teyyihan.domain.friend.abstraction

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.teyyihan.data.model.UserLocal
import com.teyyihan.data.model.entity.FriendRepresentation
import com.teyyihan.data.model.entity.Message
import com.teyyihan.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface ChatRepository {

    suspend fun sendMessage(
        text: String,
        encryptedText: ByteArray,
        friend: FriendRepresentation,
        userLocal: UserLocal
    ): Resource<Unit?>

    fun getMessageWithFriend(
        friendUsername: String
    ): Flow<PagingData<Message>>

}