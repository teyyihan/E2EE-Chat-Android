package com.teyyihan.core.util

import androidx.room.TypeConverter
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.security.PrivateKey
import java.security.PublicKey

class KeyAdapter {

    @TypeConverter
    @FromJson
    fun publicKeyFromJson(str : String) = str.convertToPublicKey()

    @TypeConverter
    @ToJson
    fun publicKeyToJson(publicKey: PublicKey) = publicKey.convertToString()

    @TypeConverter
    @FromJson
    fun privateKeyFromJson(str : String) = str.convertToPrivateKey()

    @TypeConverter
    @ToJson
    fun privateKeyToJson(privateKey: PrivateKey) = privateKey.convertToString()

}