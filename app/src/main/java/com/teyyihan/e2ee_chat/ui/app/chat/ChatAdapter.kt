package com.teyyihan.e2ee_chat.ui.app.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.teyyihan.data.model.entity.Message
import com.teyyihan.e2ee_chat.Consts
import com.teyyihan.e2ee_chat.databinding.ListItemLeftMessageBinding
import com.teyyihan.e2ee_chat.databinding.ListItemRightMessageBinding

class ChatAdapter: PagingDataAdapter<Message, RecyclerView.ViewHolder>(MESSAGE_COMPARATOR) {


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentMessage = getItem(position)
        currentMessage?.let {
            if(it.byMe){
                (holder as ChatRightMessageViewHolder).bind(it)
            }else{
                (holder as ChatLeftMessageViewHolder).bind(it)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == Consts.LEFT_MESSAGE_VIEW_TYPE){
            ChatLeftMessageViewHolder(ListItemLeftMessageBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        }else{
            ChatRightMessageViewHolder(ListItemRightMessageBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        }
    }

    override fun getItemViewType(position: Int): Int {
        getItem(position)?.let {
            if(it.byMe){
                return Consts.RIGHT_MESSAGE_VIEW_TYPE
            }
            return Consts.LEFT_MESSAGE_VIEW_TYPE
        }
        return Consts.NULL_MESSAGE_VIEW_TYPE
    }

    companion object {
        /**
         *  DiffUtil Callback to find changes and default smooth animation
         */
        private val MESSAGE_COMPARATOR = object : DiffUtil.ItemCallback<Message>() {
            override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean
                = oldItem == newItem

            override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
                return true
            }
        }
    }

}