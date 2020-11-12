package com.teyyihan.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.security.PublicKey

@Entity(tableName = "friend_table")
data class Friend(
    @PrimaryKey
    var friendUsername: String,
    var friendPublicKey: PublicKey,
    var sharedSecretKey: ByteArray,
) : Serializable

data class FriendRepresentation(
    var friendUsername: String,
    var friendPublicKey: PublicKey,
    var sharedSecretKey: ByteArray,
    @androidx.room.ColumnInfo(name = "body")
    var lastMessage : String? = null,
    @androidx.room.ColumnInfo(name = "date")
    var lastDate : String? = null,
    var byMe : Boolean? = null
) : Serializable
