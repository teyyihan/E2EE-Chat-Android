package com.teyyihan.data.remote.implementation

import com.teyyihan.data.model.request.SignUpRequest
import retrofit2.http.POST

interface SignUpAPI {

    @POST("/auth/signup")
    suspend fun signUp(signUpRequest: SignUpRequest): String

}