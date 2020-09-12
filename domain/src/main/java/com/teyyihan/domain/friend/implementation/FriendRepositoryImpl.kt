package com.teyyihan.domain.friend.implementation

import androidx.lifecycle.LiveData
import com.teyyihan.data.local.abstraction.FriendLocalDataSource
import com.teyyihan.data.model.entity.Friend
import com.teyyihan.domain.friend.abstraction.FriendRepository

class FriendRepositoryImpl(
    private val friendLocalDataSource: FriendLocalDataSource
): FriendRepository {

    override fun getAllFriends(): LiveData<List<Friend>>
        = friendLocalDataSource.getAllFriends()

    override suspend fun getFriend(username: String): Friend
        = friendLocalDataSource.getFriend(username)

}