package com.teyyihan.data.remote.implementation

import com.teyyihan.data.model.request.SignUpRequest
import com.teyyihan.data.model.response.SignUpResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpAPI {

    @POST("/auth/signup")
    suspend fun signUp(
        @Body signUpRequest: SignUpRequest
    ): SignUpResponse

}