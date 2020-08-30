package com.teyyihan.data.remote.implementation

import com.teyyihan.data.model.MessageRequest
import com.teyyihan.data.remote.abstraction.MessageRemoteDataSource


class MessageRemoteDataSourceImpl(
    private val api : MessageRemoteAPI
) : MessageRemoteDataSource{

    override suspend fun sendMessage(message: MessageRequest) : MessageRequest =
        api.sendMessageToBackend(message)

}