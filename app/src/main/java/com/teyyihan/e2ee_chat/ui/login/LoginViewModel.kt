package com.teyyihan.e2ee_chat.ui.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.teyyihan.data.model.response.TokenResponse
import com.teyyihan.data.remote.implementation.TokenAPI
import com.teyyihan.data.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginViewModel @ViewModelInject constructor(
    private val tokenAPI: TokenAPI
) : ViewModel() {

    private val TAG = "teooo LoginViewModel"

    fun token(username: String, password: String): Flow<Resource<TokenResponse>> = flow {
        try {
            val result = tokenAPI.token(username, password)
            emit(Resource.Success(result))
        }catch (e: Exception){
            emit(Resource.GenericError(errorMessage = e.localizedMessage))
        }
    }

}