package com.teyyihan.e2ee_chat.ui

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teyyihan.core.util.insertFriendIntoDB
import com.teyyihan.data.local.abstraction.FriendLocalDataSource
import com.teyyihan.data.model.entity.Friend
import com.teyyihan.data.model.response.FriendResponse
import com.teyyihan.data.remote.abstraction.ResourceRemoteDataSource
import com.teyyihan.data.util.Resource
import com.teyyihan.domain.friend.util.SessionManager
import com.teyyihan.domain.friend.util.safeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainActivityViewModel @ViewModelInject constructor(
    private val friendLocalDataSource: FriendLocalDataSource,
    private val resourceRemoteDataSource: ResourceRemoteDataSource,
    private val sessionManager: SessionManager
): ViewModel() {

    private val TAG = "teooo MainActivityViewModel"


}