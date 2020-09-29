package com.teyyihan.data.local.implementation

import android.util.Log
import androidx.lifecycle.LiveData
import com.teyyihan.data.local.abstraction.FriendLocalDataSource
import com.teyyihan.data.model.entity.Friend
import com.teyyihan.data.model.entity.FriendRepresentation

class FriendLocalDataSourceImpl(
    private val friendDao: FriendDao
): FriendLocalDataSource {

    private val TAG = "teooo FriendLocalDataSourceIm"

    override fun getAllFriends(): LiveData<List<FriendRepresentation>>
        = friendDao.getAllFriends()

    override suspend fun getFriend(username: String): Friend?
        = friendDao.getFriend(username)

    override suspend fun insertFriend(friend: Friend)
        = friendDao.insertFriend(friend)
}