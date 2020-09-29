package com.teyyihan.data.local.abstraction


import androidx.paging.DataSource
import com.teyyihan.data.model.entity.Message

interface MessageLocalDataSource {

    suspend fun insertMessage(message: Message): Long

    fun getMessagesWithFriend(friendUsername: String): DataSource.Factory<Int, Message>

}