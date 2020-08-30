package com.teyyihan.data.model

data class MessageRequest(
    val to : String,
    val data : MessageRequestData
)

data class MessageRequestData(
    val fromID: String,
    val cipherText: ByteArray
)