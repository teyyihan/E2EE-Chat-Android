package com.teyyihan.data.model.request

class MessageRequest(
    val toUsername: String,
    val fromUsername: String,
    val cipherText: ByteArray
)