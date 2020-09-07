package com.teyyihan.core.di

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.teyyihan.data.model.UserLocal
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object MoshiAdapterModule {

    @Singleton
    @Provides
    fun provideUserLocalAdapter(moshi: Moshi): JsonAdapter<UserLocal>{
        return moshi.adapter(UserLocal::class.java)
    }

}