package com.teyyihan.e2ee_chat.ui.app.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.teyyihan.data.model.entity.Friend
import com.teyyihan.data.model.entity.FriendRepresentation
import com.teyyihan.e2ee_chat.databinding.ListItemFriendBinding

class MainViewHolder(private val binding: ListItemFriendBinding): RecyclerView.ViewHolder(binding.root) {

    val root = binding.root

    fun bind(friend: FriendRepresentation) {
        binding.username.text = friend.friendUsername
    }

//    fun bind(friend: Friend) {
//        binding.username.text = friend.friendUsername
//    }


    companion object {
        fun create(
            parent: ViewGroup
        ): MainViewHolder {
            val view = ListItemFriendBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return MainViewHolder(view)
        }
    }
}