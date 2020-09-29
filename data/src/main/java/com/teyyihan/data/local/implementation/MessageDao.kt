package com.teyyihan.data.local.implementation

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.teyyihan.data.model.entity.Message

@Dao
interface MessageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: Message): Long

    @Query("SELECT * FROM message_table WHERE friendUsername = :friendUsername ORDER BY date DESC")
    fun getMessageWithFriend(friendUsername: String): DataSource.Factory<Int, Message>

}