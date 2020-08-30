package com.teyyihan.data.remote.implementation

import com.teyyihan.data.model.entity.Friend
import com.teyyihan.data.remote.abstraction.FriendRemoteDataSource

class FriendRemoteDataSourceImpl(
    private val api : FriendRemoteAPI
) : FriendRemoteDataSource{

    override suspend fun getFriendByID(friendID: String): Friend =
        api.getUserByID(friendID)

    override suspend fun getFriendByPhone(phoneNumber: String): Friend =
        api.getUserByPhoneNumber(phoneNumber)


}