package com.teyyihan.core.fake


import org.junit.Before
import org.junit.Test
import kotlinx.coroutines.runBlocking


class FriendLocalDataSourceTest {

//    //Testing
//    private lateinit var fakeFriendLocalDataSourceImpl: FriendLocalDataSource
//    private lateinit var keyUtil: KeyUtil

    @Before
    fun setup(){
//        fakeFriendLocalDataSourceImpl = FakeFriendLocalDataSourceImpl(HashMap())
//        keyUtil = KeyUtil(
//            cipher = Cipher.getInstance("DES/ECB/PKCS5Padding"),
//            keyAgreement = KeyAgreement.getInstance("DH"),
//            keyPairGenerator = KeyPairGenerator.getInstance("DH").apply {
//                initialize(2048)
//            },
//            factory = KeyFactory.getInstance("DH")
//        )
    }

    @Test
    fun test_assert_message_insertion()  = runBlocking {
//        keyUtil.generateKeys()
//        Assert.assertNotNull(keyUtil)
    }

}