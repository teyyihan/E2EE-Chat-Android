package com.teyyihan.data.remote.implementation

import com.teyyihan.data.ApiConsts
import com.teyyihan.data.model.response.TokenResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface TokenAPI {

    @POST("/auth/realms/${ApiConsts.KEYCLOAK_REALM}/protocol/openid-connect/token")
    @FormUrlEncoded
    suspend fun token(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("client_id") client_id: String = ApiConsts.CLIENT_ID,
        @Field("client_secret") client_secret: String = ApiConsts.CLIENT_SECRET,
        @Field("grant_type") grant_type: String = "password",
        ): TokenResponse


    @POST("/auth/realms/${ApiConsts.KEYCLOAK_REALM}/protocol/openid-connect/token")
    @FormUrlEncoded
    suspend fun refreshAccessToken(
        @Field("refresh_token") refreshToken: String,
        @Field("client_id") client_id: String = ApiConsts.CLIENT_ID,
        @Field("client_secret") client_secret: String = ApiConsts.CLIENT_SECRET,
        @Field("grant_type") grant_type: String = "refresh_token",
        ): TokenResponse

}