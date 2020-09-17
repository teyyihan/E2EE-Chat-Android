package com.teyyihan.e2ee_chat.ui.app.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teyyihan.core.base.BaseFragment
import com.teyyihan.e2ee_chat.databinding.FragmentChatBinding


class ChatFragment : BaseFragment() {

    private lateinit var binding: FragmentChatBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatBinding.inflate(inflater,container,false)



        return binding.root
    }

}