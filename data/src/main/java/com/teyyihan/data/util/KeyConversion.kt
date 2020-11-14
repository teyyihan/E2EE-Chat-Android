package com.teyyihan.data.util

import android.util.Base64
import android.util.Log
import java.security.KeyFactory
import java.security.PrivateKey
import java.security.PublicKey
import java.security.interfaces.ECPrivateKey
import java.security.interfaces.ECPublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import javax.crypto.interfaces.DHPrivateKey
import javax.crypto.interfaces.DHPublicKey


private val ECKeyFactory: KeyFactory = KeyFactory.getInstance("EC")

fun PublicKey.convertToString(): String? = try {
    Base64.encodeToString(this.encoded, Base64.DEFAULT)
}catch (e: Exception){
    null
}


fun String.convertToPublicKey(): PublicKey? = try {
    val publicKeyBytes = Base64.decode(this, Base64.DEFAULT)
    ECKeyFactory.generatePublic(X509EncodedKeySpec(publicKeyBytes)) as ECPublicKey
}catch (e: Exception){
    Log.d("teooo", "convertToPublicKey: AAAAAAAAAAAAAAAAAA ${e.localizedMessage}")
    null
}


fun PrivateKey.convertToString(): String? = try {
    Base64.encodeToString(this.encoded, Base64.DEFAULT)
}catch (e: Exception){
    null
}


fun String.convertToPrivateKey(): PrivateKey? = try {
    val privateKeyBytes = Base64.decode(this, Base64.DEFAULT)
    ECKeyFactory.generatePrivate(PKCS8EncodedKeySpec(privateKeyBytes)) as ECPrivateKey
}catch (e: Exception){
    null
}