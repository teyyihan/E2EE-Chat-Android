package com.teyyihan.core.util

import androidx.room.TypeConverter
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.security.PrivateKey
import java.security.PublicKey

class KeyAdapter(
    private val keyUtil: KeyUtil
) {

    @TypeConverter
    @FromJson
    fun publicKeyFromJson(str : String) = keyUtil.convertStringToPublicKey(str)

    @TypeConverter
    @ToJson
    fun publicKeyToJson(publicKey: PublicKey) = keyUtil.convertPublicKeyToString(publicKey)

    @TypeConverter
    @FromJson
    fun privateKeyFromJson(str : String) = keyUtil.convertStringToPrivateKey(str)

    @TypeConverter
    @ToJson
    fun privateKeyToJson(privateKey: PrivateKey) = keyUtil.convertPrivateKeyToString(privateKey)


}