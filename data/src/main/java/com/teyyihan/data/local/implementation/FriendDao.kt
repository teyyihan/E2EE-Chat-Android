package com.teyyihan.data.local.implementation

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.teyyihan.data.model.entity.Friend

@Dao
interface FriendDao {

    @Query("SELECT * FROM friend_table")
    fun getAllFriends(): LiveData<List<Friend>>

    @Query("SELECT * FROM friend_table WHERE friendUsername = :username")
    suspend fun getFriend(username: String): Friend


}