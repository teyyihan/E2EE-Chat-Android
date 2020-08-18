package com.teyyihan.data.remote.implementation

import com.teyyihan.data.model.MessageRemote
import com.teyyihan.data.remote.abstraction.MessageRemoteDataSource


class MessageRemoteDataSourceImpl(
    private val api : MessageRemoteAPI
) : MessageRemoteDataSource{

    override suspend fun sendMessage(message: MessageRemote) : MessageRemote =
        api.sendMessageToBackend(message)

}