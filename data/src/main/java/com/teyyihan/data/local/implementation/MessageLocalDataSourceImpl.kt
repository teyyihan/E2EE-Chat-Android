package com.teyyihan.data.local.implementation


import androidx.paging.DataSource
import com.teyyihan.data.local.abstraction.MessageLocalDataSource
import com.teyyihan.data.model.entity.Message

class MessageLocalDataSourceImpl(
    private val messageDao: MessageDao
): MessageLocalDataSource {

    override suspend fun insertMessage(message: Message): Long
        = messageDao.insertMessage(message)

    override fun getMessagesWithFriend(friendUsername: String): DataSource.Factory<Int, Message>
        = messageDao.getMessageWithFriend(friendUsername)

}