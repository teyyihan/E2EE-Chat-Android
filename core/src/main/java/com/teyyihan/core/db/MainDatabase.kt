package com.teyyihan.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.teyyihan.core.util.KeyAdapter
import com.teyyihan.data.local.implementation.FriendDao
import com.teyyihan.data.local.implementation.MessageDao
import com.teyyihan.data.model.Friend
import com.teyyihan.data.model.Message

@Database(
    entities = [Message::class, Friend::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(KeyAdapter::class)
abstract class MainDatabase : RoomDatabase(){
    abstract fun friendsDao(): FriendDao
    abstract fun messageDao(): MessageDao
}