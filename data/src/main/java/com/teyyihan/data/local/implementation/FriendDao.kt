package com.teyyihan.data.local.implementation

import androidx.room.Dao
import androidx.room.Insert
import com.teyyihan.data.local.abstraction.FriendLocalDataSource
import com.teyyihan.data.model.Friend

@Dao
interface FriendDao : FriendLocalDataSource {

    @Insert
    override suspend fun insertFriend(friend: Friend): Long

}