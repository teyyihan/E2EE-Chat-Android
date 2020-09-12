package com.teyyihan.data.remote.implementation

import com.teyyihan.data.model.request.SignUpRequest
import com.teyyihan.data.model.request.UpdateRequest
import com.teyyihan.data.model.response.TokenResponse
import com.teyyihan.data.remote.abstraction.AuthRemoteDataSource
import retrofit2.http.Field

class AuthRemoteDataSourceImpl(
    private val signUpAPI: SignUpAPI,
    private val tokenAPI: TokenAPI,
    private val resourceAPI: ResourceAPI
): AuthRemoteDataSource {

    override suspend fun getToken(username: String, password: String): TokenResponse
        = tokenAPI.token(username, password)

    override suspend fun refreshAccessToken(refreshToken: String)
        = tokenAPI.refreshAccessToken(refreshToken)

    override suspend fun signUp(signUpRequest: SignUpRequest): String
        = signUpAPI.signUp(signUpRequest)

    override suspend fun updateMe(bearerToken: String, updateRequest: UpdateRequest)
        = resourceAPI.updateMe(bearerToken,updateRequest)

}