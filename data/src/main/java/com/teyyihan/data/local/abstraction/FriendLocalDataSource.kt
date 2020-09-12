package com.teyyihan.data.local.abstraction

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.teyyihan.data.model.entity.Friend

interface FriendLocalDataSource {

    fun getAllFriends(): LiveData<List<Friend>>

    suspend fun getFriend(username: String): Friend

}