package com.teyyihan.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.security.PublicKey

@Entity(tableName = "friend_table")
data class Friend(
    @PrimaryKey
    var friendID: String,
    var friendName: String?,
    var friendPublicKey: PublicKey,
    var sharedSecretKey: ByteArray,
    var lastMessage : String? = null,
    var lastDate : String? = null,
    var lastMessageByMe : Boolean? = null
) : Serializable