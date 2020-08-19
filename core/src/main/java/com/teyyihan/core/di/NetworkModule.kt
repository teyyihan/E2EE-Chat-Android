package com.teyyihan.core.di

import com.squareup.moshi.Moshi
import com.teyyihan.core.Consts
import com.teyyihan.core.util.KeyAdapter
import com.teyyihan.data.remote.abstraction.MessageRemoteDataSource
import com.teyyihan.data.remote.implementation.MessageRemoteAPI
import com.teyyihan.data.remote.implementation.MessageRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideMoshi(keyAdapter: KeyAdapter) : Moshi =
        Moshi.Builder()
            .add(keyAdapter)
            .build()


    @Provides
    @Singleton
    fun provideMessageAPI(moshi: Moshi) : MessageRemoteAPI{
        return Retrofit.Builder()
            .baseUrl(Consts.API_BASE_URL)
            .addConverterFactory(
                MoshiConverterFactory.create(moshi)
            )
            .build()
            .create(MessageRemoteAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideMessageRemoteDataSource(messageRemoteAPI: MessageRemoteAPI) : MessageRemoteDataSource{
        return MessageRemoteDataSourceImpl(
            messageRemoteAPI
        )
    }

}