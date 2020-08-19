package com.teyyihan.core.di

import com.teyyihan.core.util.KeyAdapter
import com.teyyihan.core.util.KeyUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import java.security.KeyPairGenerator
import javax.crypto.Cipher
import javax.crypto.KeyAgreement
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class KeyModule {

    @Singleton
    @Provides
    fun provideKeyAdapter(keyUtil: KeyUtil) : KeyAdapter{
        return KeyAdapter(keyUtil)
    }

    @Singleton
    @Provides
    fun provideKeyUtil(cipher: Cipher, keyAgreement: KeyAgreement, keyPairGenerator: KeyPairGenerator) : KeyUtil{
        return KeyUtil(
            cipher,
            keyAgreement,
            keyPairGenerator
        )
    }

    @Singleton
    @Provides
    fun provideCipher() : Cipher{
        return Cipher.getInstance("DES/ECB/PKCS5Padding")
    }

    @Singleton
    @Provides
    fun provideKeyAgreement(): KeyAgreement{
        return KeyAgreement.getInstance("DH")
    }

    @Singleton
    @Provides
    fun provideKeyPairGenerator() : KeyPairGenerator{
        return KeyPairGenerator.getInstance("DH").apply {
            initialize(2048)
        }
    }

}