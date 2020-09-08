package com.teyyihan.data.remote.implementation

import com.teyyihan.data.AuthConsts
import com.teyyihan.data.model.request.LoginRequest
import com.teyyihan.data.model.response.TokenResponse
import retrofit2.http.POST

interface TokenAPI {

    @POST("/auth/realms/${AuthConsts.KEYCLOAK_REALM}/protocol/openid-connect/token")
    suspend fun token(loginRequest: LoginRequest): TokenResponse

}