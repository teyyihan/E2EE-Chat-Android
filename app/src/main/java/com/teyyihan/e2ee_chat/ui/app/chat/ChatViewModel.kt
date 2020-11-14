package com.teyyihan.e2ee_chat.ui.app.chat

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.teyyihan.domain.friend.util.SessionManager
import com.teyyihan.data.model.entity.FriendRepresentation
import com.teyyihan.data.model.entity.Message
import com.teyyihan.data.util.Resource
import com.teyyihan.domain.friend.abstraction.ChatRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChatViewModel @ViewModelInject constructor(
    private val chatRepository: ChatRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val TAG = "teooo ChatViewModel"

    fun sendMessage(text: String, friend: FriendRepresentation) = viewModelScope.launch(Dispatchers.IO) {
        val me = sessionManager.getUser()

        if(me != null){
            chatRepository.sendMessage(text,friend,me).let {
                when (it) {
                    is Resource.Success -> {
                        Log.d(TAG, "sendMessage: success ")
                    }
                    is Resource.GenericError -> {
                        Log.d(TAG, "sendMessage: error ${it.errorMessage}  ${it.exception?.localizedMessage}")
                    }

                    else -> {}
                }
            }
        }

    }

    fun getMessagesWithFriend(friendUsername: String?): LiveData<PagedList<Message>>? {
        return friendUsername?.let { chatRepository.getMessagesWithFriend(it) }
    }


}