package com.teyyihan.data.local.implementation

import androidx.paging.PagingSource
import com.teyyihan.data.local.abstraction.MessageLocalDataSource
import com.teyyihan.data.model.entity.Message

class MessageLocalDataSourceImpl(
    private val messageDao: MessageDao
): MessageLocalDataSource {

//    override fun getMessagesWithFriend(username: String): PagingSource<Int, Message>
//        = messageDao.getMessagesWithFriend(username)

}