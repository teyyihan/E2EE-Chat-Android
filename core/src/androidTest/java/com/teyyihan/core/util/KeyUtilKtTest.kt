package com.teyyihan.core.util

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.security.KeyPair


@RunWith(AndroidJUnit4::class)
class KeyUtilKtTest {

    private lateinit var keyPair: KeyPair

    @Before
    fun setup(){
        keyPair = generateKeys()
    }

    @Test
    fun test_publicKey_conversion(){
        Assert.assertEquals(keyPair.public,keyPair.public.convertToString().convertToPublicKey())
    }

    @Test
    fun test_privateKey_conversion(){
        Assert.assertEquals(keyPair.private,keyPair.private.convertToString().convertToPrivateKey())
    }

}
