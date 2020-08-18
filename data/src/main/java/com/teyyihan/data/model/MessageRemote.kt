package com.teyyihan.data.model

data class MessageRemote(
    val to : String,
    val from : String,
    val cipher : ByteArray?
)