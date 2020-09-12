package com.teyyihan.data.local.implementation

import androidx.lifecycle.LiveData
import com.teyyihan.data.local.abstraction.FriendLocalDataSource
import com.teyyihan.data.model.entity.Friend

class FriendLocalDataSourceImpl(
    private val friendDao: FriendDao
): FriendLocalDataSource {

    override fun getAllFriends(): LiveData<List<Friend>>
        = friendDao.getAllFriends()

    override suspend fun getFriend(username: String): Friend
        = friendDao.getFriend(username)
}