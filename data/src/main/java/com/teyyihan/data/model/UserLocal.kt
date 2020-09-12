package com.teyyihan.data.model

import com.teyyihan.data.model.response.TokenResponse
import java.security.PrivateKey
import java.security.PublicKey


data class UserLocal(
    val username: String,
    val publicKey: PublicKey,
    val privateKey: PrivateKey,
    var fcmToken: String,
    val token: TokenResponse
){
    fun isRefreshTokenExpired(): Boolean
        = System.currentTimeMillis() >= (token.refreshTokenSetTime + token.refresh_expires_in*1000)
}