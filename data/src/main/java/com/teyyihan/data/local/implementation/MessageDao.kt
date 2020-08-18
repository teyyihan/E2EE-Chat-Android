package com.teyyihan.data.local.implementation

import androidx.room.Dao
import androidx.room.Insert
import com.teyyihan.data.local.abstraction.MessageLocalDataSource
import com.teyyihan.data.model.Message

@Dao
interface MessageDao : MessageLocalDataSource {

    @Insert
    override suspend fun insertMessage(message: Message): Long

}