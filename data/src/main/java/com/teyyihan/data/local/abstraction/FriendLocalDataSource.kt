package com.teyyihan.data.local.abstraction

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.teyyihan.data.model.entity.Friend
import com.teyyihan.data.model.entity.FriendRepresentation

interface FriendLocalDataSource {

    fun getAllFriends(): LiveData<List<FriendRepresentation>>

    suspend fun getFriend(username: String): Friend?

    suspend fun insertFriend(friend: Friend): Long

}