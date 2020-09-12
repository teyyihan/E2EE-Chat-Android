package com.teyyihan.core.util

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.security.PrivateKey
import java.security.PublicKey

class KeySerializerAdapter {

    @FromJson
    fun publicKeyFromString(str: String): PublicKey? = str.convertToPublicKey()

    @ToJson
    fun publicKeyToString(pk: PublicKey): String? = pk.convertToString()

    @FromJson
    fun privateKeyFromString(str: String): PrivateKey? = str.convertToPrivateKey()

    @ToJson
    fun privateKeyToString(pk: PrivateKey): String? = pk.convertToString()

}