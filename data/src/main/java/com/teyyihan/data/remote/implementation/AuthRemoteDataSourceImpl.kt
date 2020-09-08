package com.teyyihan.data.remote.implementation

import com.teyyihan.data.model.request.LoginRequest
import com.teyyihan.data.model.request.SignUpRequest
import com.teyyihan.data.model.response.TokenResponse
import com.teyyihan.data.remote.abstraction.AuthRemoteDataSource

class AuthRemoteDataSourceImpl(
    private val signUpAPI: SignUpAPI,
    private val tokenAPI: TokenAPI
): AuthRemoteDataSource {

    override suspend fun getToken(loginRequest: LoginRequest): TokenResponse
        = tokenAPI.token(loginRequest)

    override suspend fun signUp(signUpRequest: SignUpRequest): String
        = signUpAPI.signUp(signUpRequest)

}