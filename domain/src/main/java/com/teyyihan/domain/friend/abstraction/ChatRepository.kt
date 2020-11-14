package com.teyyihan.domain.friend.abstraction


import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.teyyihan.data.model.UserLocal
import com.teyyihan.data.model.entity.FriendRepresentation
import com.teyyihan.data.model.entity.Message
import com.teyyihan.data.util.Resource


interface ChatRepository {

    suspend fun sendMessage(
        text: String,
        friend: FriendRepresentation,
        userLocal: UserLocal
    ): Resource<Unit?>

    fun getMessagesWithFriend(
        friendUsername: String
    ): LiveData<PagedList<Message>>?

}