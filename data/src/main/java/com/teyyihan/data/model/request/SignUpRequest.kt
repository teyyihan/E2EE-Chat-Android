package com.teyyihan.data.model.request

import java.security.PublicKey

data class SignUpRequest(
    val username: String,
    val password: String,
    val fcmToken: String,
    val publicKey: PublicKey
)