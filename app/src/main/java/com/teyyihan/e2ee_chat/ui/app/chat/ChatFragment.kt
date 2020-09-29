package com.teyyihan.e2ee_chat.ui.app.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.teyyihan.core.base.BaseFragment
import com.teyyihan.e2ee_chat.databinding.FragmentChatBinding


class ChatFragment : BaseFragment() {

    private lateinit var binding: FragmentChatBinding
    private val viewModel by viewModels<ChatViewModel>()
    private val args: ChatFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatBinding.inflate(inflater,container,false)

        binding.sendButton.setOnClickListener {
            // TODO: Check empty messages
            viewModel.sendMessage(binding.textInput.text.toString(), args.friend!!)
        }

        return binding.root
    }

}