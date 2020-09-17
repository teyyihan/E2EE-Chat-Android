package com.teyyihan.domain.friend.implementation

import androidx.paging.PagingSource
import com.teyyihan.data.local.abstraction.MessageLocalDataSource
import com.teyyihan.data.model.entity.Message
import com.teyyihan.domain.friend.abstraction.ChatRepository

class ChatRepositoryImpl(
    private val messageLocalDataSource: MessageLocalDataSource
): ChatRepository{

//    override fun getMessagesWithFriend(username: String): PagingSource<Int, Message>
//        = messageLocalDataSource.getMessagesWithFriend(username)

}