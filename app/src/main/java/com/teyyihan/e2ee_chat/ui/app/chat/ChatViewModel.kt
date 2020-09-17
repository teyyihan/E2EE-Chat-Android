package com.teyyihan.e2ee_chat.ui.app.chat

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.teyyihan.core.util.SessionManager
import com.teyyihan.data.remote.abstraction.ResourceRemoteDataSource

class ChatViewModel @ViewModelInject constructor(
    private val resourceRemoteDataSource: ResourceRemoteDataSource,
    private val sessionManager: SessionManager
): ViewModel() {



}