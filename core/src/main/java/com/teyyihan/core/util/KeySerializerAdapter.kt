package com.teyyihan.core.util

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import com.teyyihan.data.util.convertToPrivateKey
import com.teyyihan.data.util.convertToPublicKey
import com.teyyihan.data.util.convertToString
import java.security.PrivateKey
import java.security.PublicKey

@TypeConverters
class KeySerializerAdapter {

    @FromJson
    @TypeConverter
    fun publicKeyFromString(str: String): PublicKey? = str.convertToPublicKey()

    @ToJson
    @TypeConverter
    fun publicKeyToString(pk: PublicKey): String? = pk.convertToString()

    @FromJson
    @TypeConverter
    fun privateKeyFromString(str: String): PrivateKey? = str.convertToPrivateKey()

    @ToJson
    @TypeConverter
    fun privateKeyToString(pk: PrivateKey): String? = pk.convertToString()

}