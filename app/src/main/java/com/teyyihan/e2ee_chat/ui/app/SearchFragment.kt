package com.teyyihan.e2ee_chat.ui.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.teyyihan.core.base.BaseFragment
import com.teyyihan.e2ee_chat.R
import com.teyyihan.e2ee_chat.databinding.FragmentSearchBinding


class SearchFragment : BaseFragment() {

    private lateinit var binding: FragmentSearchBinding
    private val viewModel by viewModels<SearchViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater,container,false)

        binding.searchButton.setOnClickListener {
            viewModel.searchAndInsertFriend(binding.searchInput.text.toString())
        }

        return binding.root
    }

}