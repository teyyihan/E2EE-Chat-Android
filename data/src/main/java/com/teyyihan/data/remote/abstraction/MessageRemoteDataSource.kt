package com.teyyihan.data.remote.abstraction

import com.teyyihan.data.model.MessageRemote


interface MessageRemoteDataSource {

    suspend fun sendMessage(message: MessageRemote) : MessageRemote

}