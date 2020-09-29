package com.teyyihan.domain.friend.abstraction

import com.teyyihan.data.model.UserLocal
import com.teyyihan.data.model.entity.FriendRepresentation
import com.teyyihan.data.util.Resource

interface ChatRepository {


    suspend fun sendMessage(
        text: String,
        encryptedText: ByteArray,
        friend: FriendRepresentation,
        userLocal: UserLocal
    ): Resource<Unit?>


}