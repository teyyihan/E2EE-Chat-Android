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
import com.teyyihan.data.util.Resource
import com.teyyihan.domain.friend.abstraction.FriendRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel @ViewModelInject constructor(
    private val friendRepository: FriendRepository,
    private val resourceRemoteDataSource: ResourceRemoteDataSource,
    private val sessionManager: SessionManager
): ViewModel() {

    private val TAG = "teooo SearchViewModel"
    @ExperimentalCoroutinesApi
    private val _state = MutableStateFlow<Resource<String>>(Resource.NothingRN)
    @ExperimentalCoroutinesApi
    val state: StateFlow<Resource<String>>
        get() = _state

    @ExperimentalCoroutinesApi
    fun searchAndInsertFriend(username: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            val token = sessionManager.getCurrentBearerToken()
            if(token == null){
                _state.value = Resource.GenericError(errorMessage = "Invalid access token")
            }else{
                val friend = resourceRemoteDataSource.getFriend(
                    token,
                    username
                )
                Log.d(TAG, "searchAndInsertFriend: $friend")
                val isSuccessful = insertFriend(friend)
                if(isSuccessful){
                    _state.value = Resource.Success("Successfully added friend")
                }else{
                    _state.value = Resource.GenericError(errorMessage = "Something wrong with key")
                }
            }

        }catch (e: Exception){
            _state.value = Resource.GenericError(e,e.localizedMessage)
            Log.d(TAG, "searchAndInsertFriend: error ${e.localizedMessage}")
        }
    }

    private suspend fun insertFriend(friendRes: FriendResponse): Boolean {
        val friendPublicKey = friendRes.publicKey.convertToPublicKey()
        val myPrivateKey = sessionManager.getUser()?.privateKey
        val sharedSecretKey = if(myPrivateKey != null) KeyUtil.generateSharedSecret(myPrivateKey,friendPublicKey) else null
        Log.d(TAG, "insertFriend: noluyo ya ${friendPublicKey == null} ${myPrivateKey == null} ${sharedSecretKey == null}")
        if(friendPublicKey != null && myPrivateKey != null && sharedSecretKey != null){
            val friend =  Friend(
                friendRes.username,
                friendPublicKey,
                sharedSecretKey
            )
            Log.d(TAG, "insertFriend: inserted... i think ")
            friendRepository.insertFriend(friend)
            return true
        }
        return false


    }

}