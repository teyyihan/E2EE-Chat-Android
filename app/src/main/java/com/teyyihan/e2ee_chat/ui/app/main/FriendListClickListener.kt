package com.teyyihan.e2ee_chat.ui.app.main

import com.teyyihan.data.model.entity.Friend
import com.teyyihan.data.model.entity.FriendRepresentation

interface FriendListClickListener {
//    fun onFriendClicked(friend: Friend)
    fun onFriendClicked(friend: FriendRepresentation)
}