package com.teyyihan.data.model.request

import java.security.PublicKey

class MessageRequest(
    val toUsername: String,
    val fromUsername: String,
    val cipherText: ByteArray,
    val senderPublicKey: PublicKey,
    val messageSpecificIV: ByteArray,
)