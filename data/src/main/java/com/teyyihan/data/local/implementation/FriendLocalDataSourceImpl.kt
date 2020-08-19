package com.teyyihan.data.local.implementation

import com.teyyihan.data.local.abstraction.FriendLocalDataSource
import com.teyyihan.data.model.Friend

/**
 *  This is the REAL implementation of friends
 */

class FriendLocalDataSourceImpl(
    private val friendDao: FriendDao
) : FriendLocalDataSource{

    override suspend fun insertFriend(friend: Friend): Long =
        friendDao.insertFriend(friend)

    override suspend fun getFriend(friendID: String): Friend? {
        return friendDao.getFriend(friendID)
    }
}