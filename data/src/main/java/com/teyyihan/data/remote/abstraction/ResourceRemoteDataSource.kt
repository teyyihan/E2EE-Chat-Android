package com.teyyihan.data.remote.abstraction

import com.teyyihan.data.model.request.MessageRequest
import com.teyyihan.data.model.response.FriendResponse

interface ResourceRemoteDataSource {

    suspend fun getFriend(token: String, username: String): FriendResponse

    suspend fun sendMessage(message: MessageRequest)

}