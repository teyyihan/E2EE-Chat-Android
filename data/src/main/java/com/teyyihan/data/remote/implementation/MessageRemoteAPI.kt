package com.teyyihan.data.remote.implementation

import com.teyyihan.data.model.MessageRemote
import retrofit2.http.Body
import retrofit2.http.POST

interface MessageRemoteAPI {

    @POST("messages")
    suspend fun sendMessageToBackend(
        @Body message: MessageRemote
    ): MessageRemote

}