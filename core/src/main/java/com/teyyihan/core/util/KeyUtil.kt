package com.teyyihan.core.util


import android.util.Base64
import java.security.*
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import javax.crypto.interfaces.DHPrivateKey
import javax.crypto.interfaces.DHPublicKey
import kotlin.Exception

object KeyUtil {

    val keyPairGenerator = KeyPairGenerator.getInstance("DH").apply {
        initialize(2048)
    }

    fun generateKeys() : KeyPair{
        return  keyPairGenerator.generateKeyPair()
    }

}

fun PublicKey.convertToString(): String? = try {
        Base64.encodeToString(this.encoded,Base64.DEFAULT)
    }catch (e: Exception){
        null
    }


fun String.convertToPublicKey(): PublicKey? = try {
        val publicKeyBytes = Base64.decode(this,Base64.DEFAULT)
        val factory: KeyFactory = KeyFactory.getInstance("DH")
        factory.generatePublic(X509EncodedKeySpec(publicKeyBytes)) as DHPublicKey
    }catch (e: Exception){
        null
    }


fun PrivateKey.convertToString(): String? = try {
        Base64.encodeToString(this.encoded,Base64.DEFAULT)
    }catch (e: Exception){
        null
    }


fun String.convertToPrivateKey(): PrivateKey? = try {
        val privateKeyBytes = Base64.decode(this,Base64.DEFAULT)
        val factory: KeyFactory = KeyFactory.getInstance("DH")
        factory.generatePrivate(PKCS8EncodedKeySpec(privateKeyBytes)) as DHPrivateKey
    }catch (e: Exception){
        null
    }

