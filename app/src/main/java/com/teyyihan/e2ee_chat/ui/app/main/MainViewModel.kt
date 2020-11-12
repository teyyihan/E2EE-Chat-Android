package com.teyyihan.e2ee_chat.ui.app.main

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teyyihan.core.util.insertFriendIntoDB
import com.teyyihan.data.local.abstraction.FriendLocalDataSource
import com.teyyihan.data.model.entity.FriendRepresentation
import com.teyyihan.data.model.response.FriendResponse
import com.teyyihan.data.remote.abstraction.ResourceRemoteDataSource
import com.teyyihan.data.util.Resource
import com.teyyihan.domain.friend.abstraction.FriendRepository
import com.teyyihan.domain.friend.util.SessionManager
import com.teyyihan.domain.friend.util.safeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val friendRepository: FriendRepository, // TODO: Delete that replace with data source (repo viewmodel scopelu)
) : ViewModel() {

    private val TAG = "teooo MainViewModel"

    fun getFriend(): LiveData<List<FriendRepresentation>> {
        return friendRepository.getAllFriends()
    }


}