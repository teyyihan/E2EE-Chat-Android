package com.teyyihan.e2ee_chat.ui.main

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teyyihan.domain.friend.abstraction.FriendRepository
import com.teyyihan.domain.friend.implementation.FriendRepositoryImpl
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val friendRepository: FriendRepository
) : ViewModel() {

    private val TAG = "teooo MainViewModel"

    fun getFriend(){
        Log.d(TAG, "getFriend: START")
        viewModelScope.launch {
            val friends = friendRepository.getFriend("")
            Log.d(TAG, "getFriend: END $friends")
        }
    }

}