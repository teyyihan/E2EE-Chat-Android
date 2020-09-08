package com.teyyihan.data.remote.abstraction

import com.teyyihan.data.model.request.LoginRequest
import com.teyyihan.data.model.request.SignUpRequest
import com.teyyihan.data.model.response.TokenResponse

interface AuthRemoteDataSource {

    suspend fun getToken(loginRequest: LoginRequest): TokenResponse

    suspend fun signUp(signUpRequest: SignUpRequest): String

}