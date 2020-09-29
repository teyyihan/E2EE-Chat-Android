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
    var lastMessage : String? = null,
    var lastDate : String? = null,
    var byMe : Boolean? = null
) : Serializable


//@Entity(tableName = "friend_table")
//data class Friend(
//    @PrimaryKey
//    var friendUsername: String,
//    var friendPublicKey: PublicKey,
//    var sharedSecretKey: ByteArray,
//    var lastMessage : String? = null,
//    var lastDate : String? = null,
//    var byMe : Boolean? = null
//) : Serializable {
//
//    override fun equals(other: Any?): Boolean {
//        if (this === other) return true
//        if (javaClass != other?.javaClass) return false
//
//        other as Friend
//
//        if (friendUsername != other.friendUsername) return false
//        if (friendPublicKey != other.friendPublicKey) return false
//        if (!sharedSecretKey.contentEquals(other.sharedSecretKey)) return false
//        if (lastMessage != other.lastMessage) return false
//        if (lastDate != other.lastDate) return false
//        if (byMe != other.byMe) return false
//
//        return true
//    }
//
//    override fun hashCode(): Int {
//        var result = friendUsername.hashCode()
//        result = 31 * result + friendPublicKey.hashCode()
//        result = 31 * result + sharedSecretKey.contentHashCode()
//        result = 31 * result + (lastMessage?.hashCode() ?: 0)
//        result = 31 * result + (lastDate?.hashCode() ?: 0)
//        result = 31 * result + (byMe?.hashCode() ?: 0)
//        return result
//    }
//}