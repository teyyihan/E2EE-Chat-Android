package com.teyyihan.data.model

import java.util.HashSet

data class SignupRequest(
    val username: String,
    val password: String,
    val phoneNumber: String,
    val fcmToken: String,
    val publicKey: String,
    val roles: Set<String> = HashSet()
)