package com.teyyihan.data.local.implementation

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import com.teyyihan.data.local.abstraction.MessageLocalDataSource
import com.teyyihan.data.model.entity.Message

class MessageLocalDataSourceImpl(
    private val messageDao: MessageDao
): MessageLocalDataSource {

    override suspend fun insertMessage(message: Message): Long
        = messageDao.insertMessage(message)

    override fun getMessagesWithFriend(friendUsername: String): PagingSource<Int, Message>
        = messageDao.getMessagesWithFriend(friendUsername)

}