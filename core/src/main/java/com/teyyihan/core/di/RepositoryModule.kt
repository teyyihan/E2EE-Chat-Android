package com.teyyihan.core.di

import com.teyyihan.data.local.abstraction.FriendLocalDataSource
import com.teyyihan.data.local.abstraction.MessageLocalDataSource
import com.teyyihan.domain.friend.abstraction.ChatRepository
import com.teyyihan.domain.friend.abstraction.FriendRepository
import com.teyyihan.domain.friend.implementation.ChatRepositoryImpl
import com.teyyihan.domain.friend.implementation.FriendRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    @Provides
    fun provideMessageRepository(messageLocalDataSource: MessageLocalDataSource): ChatRepository{
        return ChatRepositoryImpl(messageLocalDataSource)
    }

    @Provides
    fun provideFriendRepository(friendLocalDataSource: FriendLocalDataSource): FriendRepository {
        return FriendRepositoryImpl(friendLocalDataSource)
    }

}