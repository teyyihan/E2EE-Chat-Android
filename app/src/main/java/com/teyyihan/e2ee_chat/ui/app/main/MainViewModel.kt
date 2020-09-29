package com.teyyihan.e2ee_chat.ui.app.main

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teyyihan.core.util.KeyUtil
import com.teyyihan.data.model.entity.Friend
import com.teyyihan.data.model.entity.FriendRepresentation
import com.teyyihan.domain.friend.abstraction.FriendRepository
import com.teyyihan.domain.friend.implementation.FriendRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class MainViewModel @ViewModelInject constructor(
    private val friendRepository: FriendRepository
) : ViewModel() {

    private val TAG = "teooo MainViewModel"

    fun getFriend(): LiveData<List<FriendRepresentation>> {
        return friendRepository.getAllFriends()
    }



}