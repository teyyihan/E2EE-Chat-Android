package com.teyyihan.e2ee_chat.ui.app.chat

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.teyyihan.core.base.BaseFragment
import com.teyyihan.e2ee_chat.databinding.FragmentChatBinding


class ChatFragment : BaseFragment() {

    private val TAG = "teooo ChatFragment"

    private lateinit var binding: FragmentChatBinding
    private val viewModel by viewModels<ChatViewModel>()
    private val args: ChatFragmentArgs by navArgs()
    private lateinit var adapter: ChatAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatBinding.inflate(inflater,container,false)

        setupRecycler()

        binding.sendButton.setOnClickListener {
            checkMessage(binding.textInput.text.toString())?.let{ message->
                args.friend?.let { friend->
                    viewModel.sendMessage(message, friend)
                }
                clearTextInput()
            }
        }

        viewModel.getMessagesWithFriend(args.friend?.friendUsername)?.observe(viewLifecycleOwner){
            Log.d(TAG, "onCreateView: paging list $it")
            adapter.submitList(it)
            binding.messageList.scrollToPosition(0)
        }

        return binding.root
    }

    private fun clearTextInput() {
        binding.textInput.text?.clear()
    }

    private fun setupRecycler() {
        adapter = ChatAdapter()
        binding.messageList.layoutManager = LinearLayoutManager(requireContext())
        binding.messageList.adapter = adapter
    }


    private fun checkMessage(message: String): String? {
        return if(message.isEmpty() || message.isBlank()){
            null
        }else{
            message
        }
    }

}