package com.teyyihan.data.remote.implementation

import com.teyyihan.data.model.Friend
import com.teyyihan.data.remote.abstraction.FriendRemoteDataSource

class FriendRemoteDataSourceImpl(
    private val api : FriendRemoteAPI
) : FriendRemoteDataSource{

    override suspend fun getFriend(friendID: String): Friend =
        api.getUserFromBackend(friendID)

}