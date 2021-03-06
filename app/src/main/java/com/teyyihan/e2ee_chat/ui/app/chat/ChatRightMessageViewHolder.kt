package com.teyyihan.e2ee_chat.ui.app.chat

import androidx.recyclerview.widget.RecyclerView
import com.teyyihan.data.model.entity.Message
import com.teyyihan.e2ee_chat.databinding.ListItemRightMessageBinding

class ChatRightMessageViewHolder(
    private val binding: ListItemRightMessageBinding
) : RecyclerView.ViewHolder(binding.root) {

    private val messageBody = binding.body

    fun bind(message: Message?){
        message?.let {
            messageBody.text = it.body
        }
    }

}