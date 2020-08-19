package com.teyyihan.core.fake


import com.teyyihan.core.fake.local.FakeFriendLocalDataSourceImpl
import com.teyyihan.core.util.convertToString
import com.teyyihan.core.util.generateKeys
import com.teyyihan.core.util.generateSharedSecret
import com.teyyihan.data.local.abstraction.FriendLocalDataSource
import com.teyyihan.data.model.Friend
import org.junit.Before
import org.junit.Test
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import java.security.KeyPair
import java.security.PrivateKey
import java.util.*
import javax.crypto.SecretKey
import kotlin.collections.HashMap


class FriendLocalDataSourceTest {

    //Testing
    private lateinit var fakeFriendLocalDataSourceImpl: FriendLocalDataSource
    private lateinit var mySecretKey: PrivateKey
    private lateinit var friendKeyPair : KeyPair

    @Before
    fun setup(){
        fakeFriendLocalDataSourceImpl = FakeFriendLocalDataSourceImpl(HashMap())
        mySecretKey = generateKeys().private
        friendKeyPair = generateKeys()
    }

    @Test
    fun test_assert_friend_insertion() = runBlocking {
        val sharedSecretKey = generateSharedSecret(mySecretKey, friendKeyPair.public)
        val friendID = UUID.randomUUID().toString()

        val friend = sharedSecretKey?.let {
            Friend(
                friendID = friendID,
                friendName = UUID.randomUUID().toString(),
                friendPublicKey = friendKeyPair.public,
                sharedSecretKey = it
            )
        }

        friend?.let {
            fakeFriendLocalDataSourceImpl.insertFriend(friend)
        }

        Assert.assertNotNull(fakeFriendLocalDataSourceImpl.getFriend(friendID))

    }

}