package com.teyyihan.data.util


import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.math.BigInteger
import java.security.*
import java.security.interfaces.ECPublicKey
import javax.crypto.Cipher
import javax.crypto.KeyAgreement
import javax.crypto.SecretKey
import javax.crypto.spec.DHParameterSpec
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.SecretKeySpec

object KeyUtil {

    private val TAG = "teooo KeyUtil"

    private val ECKeyPairGenerator: KeyPairGenerator = KeyPairGenerator.getInstance("EC")
    private val AESCipher: Cipher = Cipher.getInstance("AES/GCM/NoPadding")

    suspend fun generateKeypairAsPrior(): KeyPair = withContext(Dispatchers.Default) {
        ECKeyPairGenerator.initialize(256)
        return@withContext ECKeyPairGenerator.generateKeyPair()
    }

    suspend fun generateKeypairAsCustomer(publicKey: PublicKey): KeyPair = withContext(Dispatchers.Default){
        ECKeyPairGenerator.initialize((publicKey as ECPublicKey).params)
        return@withContext ECKeyPairGenerator.generateKeyPair()
    }

    fun keyAgreement(myPrivateKey: PrivateKey, othersPublicKey: PublicKey): KeyAgreement {
        val keyAgree: KeyAgreement = KeyAgreement.getInstance("ECDH")
        keyAgree.init(myPrivateKey)
        keyAgree.doPhase(othersPublicKey, true)
        return keyAgree
    }

    fun getRandomNonce(): ByteArray {
        val nonce = ByteArray(12)
        SecureRandom().nextBytes(nonce)
        return nonce
    }

    fun encryptOrDecrypt(text: ByteArray, key: SecretKey, mode: Int/*Cipher.DECRYPT_MODE*/, iv: ByteArray): ByteArray{
        val gcmParameterSpec = GCMParameterSpec(128, iv)
        AESCipher.init(mode, key, gcmParameterSpec)
        return AESCipher.doFinal(text)
    }

    fun generateSharedAESKey(keyAgreement: KeyAgreement): SecretKey = keyAgreement.generateSecret("AES")

}

