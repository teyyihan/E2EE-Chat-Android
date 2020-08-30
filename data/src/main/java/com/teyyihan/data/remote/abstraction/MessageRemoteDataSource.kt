package com.teyyihan.data.remote.abstraction

import com.teyyihan.data.model.MessageRequest


interface MessageRemoteDataSource {

    suspend fun sendMessage(message: MessageRequest) : MessageRequest

}