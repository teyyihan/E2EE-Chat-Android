package com.teyyihan.data.local.abstraction

import com.teyyihan.data.model.Message

interface MessageLocalDataSource {

    suspend fun insertMessage(message : Message) : Long

}