package com.teyyihan.data.model.response

import java.security.PublicKey

data class SignUpResponse(
    val username: String,
    val fcmToken: String,
    val publicKey: PublicKey,
)