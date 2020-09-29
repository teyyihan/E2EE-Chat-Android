package com.teyyihan.e2ee_chat.ui.app.chat

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.teyyihan.core.base.BaseFragment
import com.teyyihan.e2ee_chat.databinding.FragmentChatBinding
import kotlinx.coroutines.flow.collectLatest


class ChatFragment : BaseFragment() {

    private val TAG = "teooo ChatFragment"

    private lateinit var binding: FragmentChatBinding
    private val viewModel by viewModels<ChatViewModel>()
    private val args: ChatFragmentArgs by navArgs()
    private lateinit var adapter : ChatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ChatAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatBinding.inflate(inflater,container,false)

        setupRecyclerView()

        binding.sendButton.setOnClickListener {
            // TODO: Check empty messages
            viewModel.sendMessage(binding.textInput.text.toString(), args.friend!!)
        }

        lifecycleScope.launchWhenResumed {
            viewModel.getMessagesLive(args.friend?.friendUsername)?.collectLatest {
                adapter.submitData(it)
            }
        }


        return binding.root
    }

    private fun setupRecyclerView() {
        binding.messageList.layoutManager = LinearLayoutManager(requireContext())
        binding.messageList.adapter = adapter
    }

}