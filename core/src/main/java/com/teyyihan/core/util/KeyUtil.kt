package com.teyyihan.core.util


import android.util.Base64
import java.security.*
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import javax.crypto.KeyAgreement
import javax.crypto.interfaces.DHPrivateKey
import javax.crypto.interfaces.DHPublicKey
import kotlin.Exception

object KeyUtil {

    private val keyAgreement: KeyAgreement = KeyAgreement.getInstance("DH")

    private val keyPairGenerator = KeyPairGenerator.getInstance("DH").apply {
        initialize(2048)
    }

    fun generateKeys() : KeyPair{
        return  keyPairGenerator.generateKeyPair()
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

    private fun shortenSecretKey(longkey : ByteArray) : ByteArray?{
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

