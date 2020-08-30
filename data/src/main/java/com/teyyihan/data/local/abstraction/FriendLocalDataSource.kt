package com.teyyihan.data.local.abstraction

import com.teyyihan.data.model.entity.Friend

interface FriendLocalDataSource {

    suspend fun insertFriend(friend: Friend) : Long

    suspend fun getFriend(friendID: String) : Friend?

}