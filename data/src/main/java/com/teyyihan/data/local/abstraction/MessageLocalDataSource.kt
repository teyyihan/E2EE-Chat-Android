package com.teyyihan.data.local.abstraction


import com.teyyihan.data.model.entity.Message

interface MessageLocalDataSource {

    suspend fun insertMessage(message : Message) : Long

    suspend fun getMessage(messageID : Int) : Message?

}