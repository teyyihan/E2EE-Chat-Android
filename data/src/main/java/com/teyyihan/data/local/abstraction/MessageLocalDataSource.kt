package com.teyyihan.data.local.abstraction

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import com.teyyihan.data.model.entity.Message

interface MessageLocalDataSource {

    suspend fun insertMessage(message: Message): Long

    fun getMessagesWithFriend(friendUsername: String): PagingSource<Int,Message>


}