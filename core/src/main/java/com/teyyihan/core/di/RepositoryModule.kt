package com.teyyihan.core.di

import com.teyyihan.data.local.abstraction.FriendLocalDataSource
import com.teyyihan.domain.friend.abstraction.FriendRepository
import com.teyyihan.domain.friend.implementation.FriendRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    @Provides
    fun provideFriendRepository(friendLocalDataSource: FriendLocalDataSource): FriendRepository {
        return FriendRepositoryImpl(friendLocalDataSource)
    }

}