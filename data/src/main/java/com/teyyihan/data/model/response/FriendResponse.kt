package com.teyyihan.data.model.response

import java.security.PublicKey

data class FriendResponse(
    val username: String,
    val publicKey: PublicKey,
)