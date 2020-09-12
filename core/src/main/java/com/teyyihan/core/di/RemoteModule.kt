package com.teyyihan.core.di

import com.squareup.moshi.Moshi
import com.teyyihan.data.ApiConsts
import com.teyyihan.data.remote.implementation.ResourceAPI
import com.teyyihan.data.remote.implementation.SignUpAPI
import com.teyyihan.data.remote.implementation.TokenAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RemoteModule {


    @Singleton
    @Provides
    fun provideResourceAPI(@SpringRetrofit retrofit: Retrofit): ResourceAPI{
        return retrofit.create(ResourceAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideTokenAPI(@KeycloakRetrofit retrofit: Retrofit): TokenAPI{
        return retrofit.create(TokenAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideSignupAPI(@SpringRetrofit retrofit: Retrofit): SignUpAPI{
        return retrofit.create(SignUpAPI::class.java)
    }

    @Singleton
    @Provides
    @KeycloakRetrofit
    fun provideKeycloakRetrofit(retrofitBuilder: Retrofit.Builder): Retrofit{
        return retrofitBuilder
            .baseUrl(ApiConsts.KEYCLOAK_BASE_RUL)
            .build()
    }

    @Singleton
    @Provides
    @SpringRetrofit
    fun provideSpringRetrofit(retrofitBuilder: Retrofit.Builder): Retrofit{
        return retrofitBuilder
            .baseUrl(ApiConsts.SPRING_BASE_RUL)
            .build()
    }


    @Singleton
    @Provides
    fun provideRetrofitBuilder(moshiConverterFactory: MoshiConverterFactory): Retrofit.Builder{
        return Retrofit.Builder()
            .addConverterFactory(moshiConverterFactory)
    }

    @Singleton
    @Provides
    fun provideMoshiConverterFactory(moshi: Moshi): MoshiConverterFactory{
        return MoshiConverterFactory.create(moshi)
    }

}