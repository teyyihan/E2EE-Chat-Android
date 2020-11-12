package com.teyyihan.e2ee_chat.ui.app.main

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.teyyihan.data.model.entity.Friend
import com.teyyihan.data.model.entity.FriendRepresentation

class MainListAdapter(): ListAdapter<FriendRepresentation, MainViewHolder>(DIFF_UTIL) {

    lateinit var friendListClickListener: FriendListClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder.create(
            parent
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val friend = getItem(position)

        holder.bind(friend)

        holder.root.setOnClickListener {
            friendListClickListener.onFriendClicked(friend)
        }

    }


    companion object{
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<FriendRepresentation>(){
            override fun areItemsTheSame(oldItem: FriendRepresentation, newItem: FriendRepresentation): Boolean
                    = oldItem == newItem

            override fun areContentsTheSame(oldItem: FriendRepresentation, newItem: FriendRepresentation): Boolean
                = true
        }
    }
}
