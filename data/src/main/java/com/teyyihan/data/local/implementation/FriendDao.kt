package com.teyyihan.data.local.implementation

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.teyyihan.data.model.entity.Friend

@Dao
interface FriendDao {

    @Insert
    suspend fun insertFriend(friend: Friend): Long

    @Query("SELECT * FROM friend_table WHERE friendID = :friendID")
    fun getFriend(friendID: String): Friend?

}