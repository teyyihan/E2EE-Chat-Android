package com.teyyihan.core.di

import android.content.Context
import android.content.SharedPreferences
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.squareup.moshi.Moshi
import com.teyyihan.core.db.MainDatabase
import com.teyyihan.core.util.KeySerializerAdapter
import com.teyyihan.data.local.abstraction.FriendLocalDataSource
import com.teyyihan.data.local.abstraction.MessageLocalDataSource
import com.teyyihan.data.local.implementation.FriendLocalDataSourceImpl
import com.teyyihan.data.local.implementation.MessageLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object CacheModule {


    @Provides
    fun provideMessageLocalDataSource(db: MainDatabase): MessageLocalDataSource{
        return MessageLocalDataSourceImpl(db.messageDao())
    }

    @Provides
    fun provideFriendLocalDataSource(db: MainDatabase): FriendLocalDataSource{
        return FriendLocalDataSourceImpl(db.friendDao())
    }


    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MainDatabase{
//        return Room.databaseBuilder(
//            context,
//            MainDatabase::class.java,
//            Consts.DATABASE_NAME
//        )
//            .build()
        return MainDatabase.getInstance(context)
    }

    private val keyGenParameterSpec = KeyGenParameterSpec.Builder(
        MasterKey.DEFAULT_MASTER_KEY_ALIAS,
        KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
    ).setBlockModes(KeyProperties.BLOCK_MODE_GCM)
        .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
        .setKeySize(256)
        .build()

    @Provides
    fun provideEncryptedSP(@ApplicationContext context: Context): SharedPreferences{
        val masterKey = MasterKey.Builder(context)
            .setKeyGenParameterSpec(keyGenParameterSpec)
            .build()
        return EncryptedSharedPreferences.create(
            context,
            "encrypted_sp",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KeySerializerAdapter())
            .build()
    }


}