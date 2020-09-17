package com.teyyihan.data.local.implementation

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.teyyihan.data.model.entity.Message

@Dao
interface MessageDao {

//    @Query("SELECT * FROM message_table WHERE friendName = :username")
//    fun getMessagesWithFriend(username: String): PagingSource<Int, Message>

}