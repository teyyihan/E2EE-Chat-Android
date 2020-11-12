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

    fun searchAndInsertFriend(friendUsername: String) = flow {
        emit(Resource.Loading)
        val friend: Resource<FriendResponse?> = safeApiCall(sessionManager){
            resourceRemoteDataSource.getFriend(it,friendUsername)
        }
        if(friend is Resource.Success){
            friend.value?.let {
                val friendInserted = insertFriendIntoDB(it,sessionManager,friendLocalDataSource)
                if(friendInserted != null){
                    emit(Resource.Success(friendInserted))
                }else{
                    emit(Resource.GenericError(errorMessage = "Couldn't insert friend"))
                }
            }
        }else if(friend is Resource.GenericError){
            Log.d(TAG, "handleError: ${friend.exception?.localizedMessage}  ${friend.errorMessage}")
            emit(Resource.GenericError(friend.exception,friend.errorMessage))
        }
    }




}