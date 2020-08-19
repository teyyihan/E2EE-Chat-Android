package com.teyyihan.data.local.implementation

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.teyyihan.data.local.abstraction.MessageLocalDataSource
import com.teyyihan.data.model.Message

@Dao
interface MessageDao {

    @Insert
    suspend fun insertMessage(message: Message): Long

    @Query("SELECT * FROM message_table WHERE _id = :messageID")
    suspend fun getMessage(messageID: Int): Message?

}