package com.teyyihan.data.remote.implementation

import com.teyyihan.data.model.request.MessageRequest
import com.teyyihan.data.model.request.UpdateRequest
import com.teyyihan.data.model.response.FriendResponse
import retrofit2.http.*

interface ResourceAPI {

    @POST("/resource/user/update")
    suspend fun updateMe(
        @Header("Authorization") authHeader: String,
        @Body updateRequest: UpdateRequest
    )

    @GET("/resource/user")
    suspend fun getFriend(
        @Header("Authorization") authHeader: String,
        @Query("username") username: String
    ): FriendResponse

    @POST("/resource/send")
    suspend fun sendMessage(
        @Header("Authorization") authHeader: String,
        @Body messageRequest: MessageRequest
    )

}