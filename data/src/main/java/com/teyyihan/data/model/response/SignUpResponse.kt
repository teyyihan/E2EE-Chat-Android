package com.teyyihan.data.model.response

data class SignUpResponse(
    val username: String,
    val fcmToken: String,
    val publicKey: String,
)