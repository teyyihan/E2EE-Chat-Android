package com.teyyihan.data.model.request

data class SignUpRequest(
    val username: String,
    val password: String,
    val fcmToken: String,
    val publicKey: String
)