package com.teyyihan.e2ee_chat.ui.app.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.teyyihan.data.model.entity.Message
import com.teyyihan.e2ee_chat.Consts
import com.teyyihan.e2ee_chat.databinding.ListItemLeftMessageBinding
import com.teyyihan.e2ee_chat.databinding.ListItemRightMessageBinding

class ChatAdapter : PagedListAdapter<Message, RecyclerView.ViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            Consts.LEFT_MESSAGE_VIEW_TYPE -> {
                ChatLeftMessageViewHolder(ListItemLeftMessageBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            }
            else -> {
                ChatRightMessageViewHolder(ListItemRightMessageBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let {
            if(!it.byMe){
                (holder as ChatLeftMessageViewHolder).bind(it)
            }else{
                (holder as ChatRightMessageViewHolder).bind(it)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        getItem(position)?.let {
            return if(it.byMe){
                Consts.RIGHT_MESSAGE_VIEW_TYPE
            } else{
                Consts.LEFT_MESSAGE_VIEW_TYPE
            }

        }

        return Consts.NULL_MESSAGE_VIEW_TYPE
    }

    companion object{
        val COMPARATOR = object : DiffUtil.ItemCallback<Message>(){
            override fun areItemsTheSame(oldItem: Message, newItem: Message) = true
            override fun areContentsTheSame(oldItem: Message, newItem: Message) = true
        }
    }
}