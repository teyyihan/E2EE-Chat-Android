package com.teyyihan.domain.friend.abstraction

import androidx.lifecycle.LiveData
import com.teyyihan.data.model.entity.Friend

interface FriendRepository {

    fun getAllFriends(): LiveData<List<Friend>>

    suspend fun getFriend(username: String): Friend

    suspend fun insertFriend(friend: Friend): Long

}