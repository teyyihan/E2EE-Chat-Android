package com.teyyihan.data.local.implementation

import com.teyyihan.data.local.abstraction.FriendLocalDataSource
import com.teyyihan.data.model.Friend
import javax.inject.Inject

/**
 *  This is the REAL implementation of friends
 */

class FriendLocalDataSourceImpl
@Inject
constructor(
    private val friendDao: FriendDao
) : FriendLocalDataSource{

    override suspend fun insertFriend(friend: Friend): Long =
        friendDao.insertFriend(friend)
}