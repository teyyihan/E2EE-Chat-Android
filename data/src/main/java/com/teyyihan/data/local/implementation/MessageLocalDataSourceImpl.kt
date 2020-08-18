package com.teyyihan.data.local.implementation

import com.teyyihan.data.local.abstraction.MessageLocalDataSource
import com.teyyihan.data.model.Message
import javax.inject.Inject

/**
 *  This is the REAL implementation of messages
 */

class MessageLocalDataSourceImpl
@Inject
constructor(
    private val messageDao: MessageDao
) : MessageLocalDataSource
{
    override suspend fun insertMessage(message: Message): Long =
        messageDao.insertMessage(message)
}