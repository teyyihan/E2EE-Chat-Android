package com.teyyihan.e2ee_chat.ui.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.teyyihan.e2ee_chat.databinding.BottomsheetFriendSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFriendBottomSheet: BottomSheetDialogFragment() {

    private lateinit var binding: BottomsheetFriendSearchBinding
    private val viewModel by viewModels<SearchViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomsheetFriendSearchBinding.inflate(inflater,container,false)

        binding.searchButton.setOnClickListener {
            val inputText = binding.searchEditText.text.toString()
            if(inputText.isNotBlank() || inputText.isEmpty()){
                searchFriend(inputText)
            }
        }

        return binding.root
    }

    private fun searchFriend(inputText: String) {
        viewModel.searchAndInsertFriend(inputText)
    }

}