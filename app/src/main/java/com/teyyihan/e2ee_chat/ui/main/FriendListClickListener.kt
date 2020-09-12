package com.teyyihan.e2ee_chat.ui.main

import com.teyyihan.data.model.entity.Friend

interface FriendListClickListener {
    fun onFriendClicked(friend: Friend)
}