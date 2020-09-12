package com.teyyihan.data.remote.implementation

import com.teyyihan.data.model.request.UpdateRequest
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ResourceAPI {

    @POST("/resource/user/update")
    suspend fun updateMe(
        @Header("Authorization") authHeader: String,
        @Body updateRequest: UpdateRequest
    )

}