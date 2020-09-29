package com.teyyihan.data.local.implementation

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.teyyihan.data.model.entity.Message

@Dao
interface MessageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: Message): Long

    @Query("SELECT * FROM message_table WHERE friendUsername = :friendUsername")
    fun getMessagesWithFriend(friendUsername: String): PagingSource<Int, Message>

}