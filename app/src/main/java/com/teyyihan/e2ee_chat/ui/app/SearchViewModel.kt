package com.teyyihan.e2ee_chat.ui.app

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teyyihan.domain.friend.util.AuthState
import com.teyyihan.core.util.KeyUtil
import com.teyyihan.domain.friend.util.SessionManager
import com.teyyihan.core.util.convertToPublicKey
import com.teyyihan.data.model.entity.Friend
import com.teyyihan.data.model.response.FriendResponse
import com.teyyihan.data.remote.abstraction.ResourceRemoteDataSource
import com.teyyihan.domain.friend.abstraction.FriendRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel @ViewModelInject constructor(
    private val friendRepository: FriendRepository,
    private val resourceRemoteDataSource: ResourceRemoteDataSource,
    private val sessionManager: SessionManager
): ViewModel() {

    private val TAG = "teooo SearchViewModel"

    fun searchAndInsertFriend(username: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val token = "Bearer ${(sessionManager.authState.value!! as AuthState.Success).value.peekContent().token.access_token}"
            val friend = resourceRemoteDataSource.getFriend(
                token,
                username
            )
            Log.d(TAG, "searchAndInsertFriend: $friend")
            insertFriend(friend)
        }catch (e: Exception){
            Log.d(TAG, "searchAndInsertFriend: error ${e.localizedMessage}")
        }
    }

    private suspend fun insertFriend(friendRes: FriendResponse) {
        val friend =  Friend(
            friendRes.username,
            friendRes.publicKey.convertToPublicKey()!!,
            KeyUtil.generateSharedSecret((sessionManager.authState.value!! as AuthState.Success).value.peekContent().privateKey,friendRes.publicKey.convertToPublicKey()!!)!!
        )
        Log.d(TAG, "insertFriend: inserted... i think ${friendRepository.insertFriend(friend)}")
    }

}