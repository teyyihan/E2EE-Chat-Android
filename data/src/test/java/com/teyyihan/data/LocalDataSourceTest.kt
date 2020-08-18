package com.teyyihan.data

import com.teyyihan.data.model.Message
import com.teyyihan.data.data.local.FakeMessageLocalDataSourceImpl
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import kotlin.collections.HashMap

class LocalDataSourceTest {

   private lateinit var fakeMessageLocalDataSourceImpl: FakeMessageLocalDataSourceImpl

    @Before
    fun setup(){
        fakeMessageLocalDataSourceImpl = FakeMessageLocalDataSourceImpl(HashMap())
    }

    @Test
    fun test_assert_message_insertion() = runBlocking {
        val message = Message(
            1,
            "1",
            "Message",
            "11.03.1999",
            false
        )
        fakeMessageLocalDataSourceImpl.insertMessage(message)

        message._id?.let {
            Assert.assertTrue(fakeMessageLocalDataSourceImpl.map.contains(it.toString()))
        }
        return@runBlocking
    }

    @Test
    fun test_assert_friend_insertion() = runBlocking {

    }

}