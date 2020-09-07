package com.teyyihan.data.model

import com.teyyihan.data.model.response.TokenResponse
import java.security.PrivateKey
import java.security.PublicKey


data class UserLocal(
    val username: String,
    val publicKey: PublicKey,
    val privateKey: PrivateKey,
    val fcmToken: String,
    val token: TokenResponse
){
    fun isRefreshTokenExpired(): Boolean
        = System.currentTimeMillis() >= (token.time + token.expires_in)
}