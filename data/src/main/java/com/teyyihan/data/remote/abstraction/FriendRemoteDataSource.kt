package com.teyyihan.data.remote.abstraction

import com.teyyihan.data.model.Friend

interface FriendRemoteDataSource {

    suspend fun getFriend(friendID : String) : Friend

}