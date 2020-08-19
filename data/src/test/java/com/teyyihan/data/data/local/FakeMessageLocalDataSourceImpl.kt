package com.teyyihan.data.data.local


import com.teyyihan.data.local.abstraction.MessageLocalDataSource
import com.teyyihan.data.model.Message

class FakeMessageLocalDataSourceImpl(
    val map : HashMap<String, Message>
) : MessageLocalDataSource {

    override suspend fun insertMessage(message: Message): Long {
        message._id?.let {
            map.put(it.toString(), message)
        }
        return 1L
    }

    override suspend fun getMessage(messageID: String): Message? {
        return map.get(messageID)
    }

}