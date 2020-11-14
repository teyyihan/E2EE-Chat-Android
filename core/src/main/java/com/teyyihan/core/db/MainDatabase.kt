package com.teyyihan.core.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.teyyihan.core.util.KeySerializerAdapter
import com.teyyihan.data.local.implementation.FriendDao
import com.teyyihan.data.local.implementation.MessageDao
import com.teyyihan.data.model.entity.Friend
import com.teyyihan.data.model.entity.Message
import com.teyyihan.domain.friend.Consts

@Database(
    entities = [Friend::class, Message::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(KeySerializerAdapter::class)
abstract class MainDatabase : RoomDatabase() {

    abstract fun friendDao(): FriendDao
    abstract fun messageDao(): MessageDao


    /**
     *  Will be called from ViewModelModule DI
     */
    companion object {

        @Volatile
        private var INSTANCE: MainDatabase? = null

        fun getInstance(context: Context): MainDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                MainDatabase::class.java, Consts.DATABASE_NAME)
                .build()
    }
}