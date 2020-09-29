package com.teyyihan.e2ee_chat.ui.app.chat

import androidx.recyclerview.widget.RecyclerView
import com.teyyihan.data.model.entity.Message
import com.teyyihan.e2ee_chat.databinding.ListItemLeftMessageBinding

class ChatViewHolder(
    private val binding: ListItemLeftMessageBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(message: Message){
        binding.body.text = message.body
    }

}