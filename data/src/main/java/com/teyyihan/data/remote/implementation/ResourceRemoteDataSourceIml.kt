package com.teyyihan.data.remote.implementation

import com.teyyihan.data.model.request.MessageRequest
import com.teyyihan.data.model.response.FriendResponse
import com.teyyihan.data.remote.abstraction.ResourceRemoteDataSource

class ResourceRemoteDataSourceIml(
    private val resourceAPI: ResourceAPI
): ResourceRemoteDataSource {

    override suspend fun getFriend(token: String, username: String): FriendResponse
        = resourceAPI.getFriend(token, username)

    override suspend fun sendMessage(token: String, message: MessageRequest)
        = resourceAPI.sendMessage(token, message)

}