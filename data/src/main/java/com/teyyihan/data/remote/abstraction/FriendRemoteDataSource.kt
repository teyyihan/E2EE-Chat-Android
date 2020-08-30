package com.teyyihan.data.remote.abstraction

import com.teyyihan.data.model.entity.Friend

interface FriendRemoteDataSource {

    suspend fun getFriendByID(friendID : String) : Friend

    suspend fun getFriendByPhone(phoneNumber: String): Friend

}