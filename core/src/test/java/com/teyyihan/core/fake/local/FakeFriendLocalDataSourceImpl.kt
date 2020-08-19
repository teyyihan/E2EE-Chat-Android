package com.teyyihan.core.fake.local

import com.teyyihan.data.local.abstraction.FriendLocalDataSource
import com.teyyihan.data.model.Friend

class FakeFriendLocalDataSourceImpl(
    private val map : HashMap<String, Friend>
) : FriendLocalDataSource{

    override suspend fun insertFriend(friend: Friend): Long {
        map.put(friend.friendID,friend)
        return 1L
    }

}