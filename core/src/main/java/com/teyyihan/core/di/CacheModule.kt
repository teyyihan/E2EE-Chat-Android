package com.teyyihan.core.di

import android.content.Context
import androidx.room.Room
import com.teyyihan.core.Consts
import com.teyyihan.core.db.MainDatabase
import com.teyyihan.data.local.abstraction.FriendLocalDataSource
import com.teyyihan.data.local.abstraction.MessageLocalDataSource
import com.teyyihan.data.local.implementation.FriendDao
import com.teyyihan.data.local.implementation.FriendLocalDataSourceImpl
import com.teyyihan.data.local.implementation.MessageDao
import com.teyyihan.data.local.implementation.MessageLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class CacheModule {

    @Singleton
    @Provides
    fun provideMessageLocalDataSource(messageDao: MessageDao): MessageLocalDataSource{
        return MessageLocalDataSourceImpl(messageDao)
    }

    @Singleton
    @Provides
    fun provideFriendLocalDataSource(friendDao: FriendDao): FriendLocalDataSource{
        return FriendLocalDataSourceImpl(friendDao)
    }

    @Singleton
    @Provides
    fun provideMessageDao(database: MainDatabase) : MessageDao{
        return database.messageDao()
    }

    @Singleton
    @Provides
    fun provideFriendDao(database: MainDatabase) : FriendDao{
        return database.friendsDao()
    }

    @Singleton
    @Provides
    fun provideMainDatabase(@ApplicationContext context: Context) : MainDatabase {
        return  Room.databaseBuilder(
            context,
            MainDatabase::class.java,
            Consts.DATABASE_NAME
        ).build()
    }

}