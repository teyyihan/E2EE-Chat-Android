package com.teyyihan.core.util


import java.security.*
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import javax.crypto.Cipher
import javax.crypto.KeyAgreement
import javax.crypto.interfaces.DHPrivateKey
import javax.crypto.interfaces.DHPublicKey
import javax.crypto.spec.SecretKeySpec


val cipher: Cipher = Cipher.getInstance("DES/ECB/PKCS5Padding")

val keyAgreement: KeyAgreement = KeyAgreement.getInstance("DH")

val keyPairGenerator: KeyPairGenerator = KeyPairGenerator.getInstance("DH").apply {
    initialize(2048)
}


fun String.encryptMessage(secretKey : ByteArray) : ByteArray?{
    return try {
        val secretKeySpec = SecretKeySpec(secretKey,0,8,"DES")
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec)
        cipher.doFinal(toByteArray())
    }catch (e:Exception){
        null
    }
}

fun decryptMessage( message : ByteArray, secretKey: ByteArray) : String?{
    return try {
        val secretKeySpec = SecretKeySpec(secretKey,0,8,"DES")
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec)
        String(cipher.doFinal(message))
    } catch (e: Exception) {
        null
    }
}

fun generateKeys() : KeyPair?{
    return try {
        val keyPair: KeyPair = keyPairGenerator.generateKeyPair()
        keyPair
    } catch (e: Exception) {
        null
    }
}

fun generateSharedSecret(myPrivateKey : PrivateKey?, friendPublicKey : PublicKey?) : ByteArray?{
    return try {
        keyAgreement.init(myPrivateKey)
        keyAgreement.doPhase(friendPublicKey, true)
        shortenSecretKey(keyAgreement.generateSecret())
    } catch (e: java.lang.Exception) {
        null
    }
}

fun shortenSecretKey(longkey : ByteArray) : ByteArray?{
    return try {
        // Use 8 bytes (64 bits) for DES, 6 bytes (48 bits) for Blowfish
        val shortenedKey = ByteArray(8)
        System.arraycopy(longkey, 0, shortenedKey, 0, shortenedKey.size);
        shortenedKey
    } catch (e:Exception) {
        e.printStackTrace()
        null
    }
}


fun PublicKey.convertToString() : String{
    val bytePubkey: ByteArray = this.encoded
    val strKey: String = android.util.Base64.encodeToString(bytePubkey,0)
    return strKey
}


fun String.convertToPublicKey() : PublicKey{
    val bytePubkey = android.util.Base64.decode(this,0)
    val factory: KeyFactory = KeyFactory.getInstance("DH")
    val publicKey = factory.generatePublic(X509EncodedKeySpec(bytePubkey)) as DHPublicKey
    return publicKey
}


fun PrivateKey.convertToString() : String{
    val byte_privkey: ByteArray = this.encoded
    val str_key: String = android.util.Base64.encodeToString(byte_privkey,0)
    return str_key
}


fun String.convertToPrivateKey() : PrivateKey{
    val byte_privkey = android.util.Base64.decode(this,0)
    val factory: KeyFactory = KeyFactory.getInstance("DH")
    val private_key = factory.generatePrivate(PKCS8EncodedKeySpec(byte_privkey)) as DHPrivateKey
    return private_key
}

