package com.teyyihan.core.util

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.squareup.moshi.Moshi
import com.teyyihan.data.model.UserLocal
import com.teyyihan.data.model.response.TokenResponse
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.security.KeyPair

@RunWith(AndroidJUnit4::class)
class KeyUtilTest {

    private val TAG = "teooo KeyUtilTest"

    private lateinit var moshi: Moshi
    private lateinit var keyPair: KeyPair

    @Before
    fun setup(){
        moshi = Moshi.Builder()
            .add(KeySerializerAdapter())
            .build()
        keyPair = KeyUtil.generateKeys()
    }

    @Test
    fun localUserSerialization_test(){
        val user = UserLocal(
            "username",
            keyPair.public,
            keyPair.private,
            "fcmToken",
            TokenResponse(
                "access_token",
                300,
                1800,
                "refresh_token",
                "bearer"
            )
        )

        val jsonAdapter = moshi.adapter(UserLocal::class.java)
        val str = jsonAdapter.toJson(user)

        Log.d(TAG, "test1: $str")

        Assert.assertEquals(user, jsonAdapter.fromJson(str))

    }

}