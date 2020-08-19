package com.teyyihan.data.local.abstraction

import com.teyyihan.data.model.Friend

interface FriendLocalDataSource {

    suspend fun insertFriend(friend: Friend) : Long

    suspend fun getFriend(friendID: String) : Friend?

}