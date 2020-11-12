package com.teyyihan.domain.friend.abstraction

import androidx.lifecycle.LiveData
import com.teyyihan.data.model.entity.Friend
import com.teyyihan.data.model.entity.FriendRepresentation

interface FriendRepository {

    fun getAllFriends(): LiveData<List<FriendRepresentation>>

    suspend fun getFriend(username: String): Friend?

    suspend fun insertFriend(friend: Friend): Long

}