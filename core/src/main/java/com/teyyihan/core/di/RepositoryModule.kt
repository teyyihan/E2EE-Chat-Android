package com.teyyihan.core.di

import com.teyyihan.data.local.abstraction.FriendLocalDataSource
import com.teyyihan.data.local.abstraction.MessageLocalDataSource
import com.teyyihan.data.remote.abstraction.ResourceRemoteDataSource
import com.teyyihan.domain.friend.abstraction.ChatRepository
import com.teyyihan.domain.friend.abstraction.FriendRepository
import com.teyyihan.domain.friend.implementation.ChatRepositoryImpl
import com.teyyihan.domain.friend.implementation.FriendRepositoryImpl
import com.teyyihan.domain.friend.util.SessionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    @Provides
    fun provideMessageRepository(
        messageLocalDataSource: MessageLocalDataSource,
        resourceRemoteDataSource: ResourceRemoteDataSource,
        sessionManager: SessionManager
    ): ChatRepository {
        return ChatRepositoryImpl(messageLocalDataSource, resourceRemoteDataSource,sessionManager)
    }

    @Provides
    fun provideFriendRepository(friendLocalDataSource: FriendLocalDataSource): FriendRepository {
        return FriendRepositoryImpl(friendLocalDataSource)
    }

}