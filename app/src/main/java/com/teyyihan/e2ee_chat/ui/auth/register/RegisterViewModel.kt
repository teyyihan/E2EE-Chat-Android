package com.teyyihan.e2ee_chat.ui.auth.register

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.iid.FirebaseInstanceId
import com.teyyihan.data.model.UserLocal
import com.teyyihan.data.util.KeyUtil
import com.teyyihan.data.util.Resource
import com.teyyihan.domain.friend.util.AuthStep
import com.teyyihan.domain.friend.util.SessionManager
import com.teyyihan.e2ee_chat.util.awaitTask
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.security.KeyPair

class RegisterViewModel @ViewModelInject constructor(
    private val sessionManager: SessionManager
) : ViewModel() {

    private val TAG = "teooo RegisterViewModel"

    fun registerFlow(username: String, password: String) = viewModelScope.launch(Dispatchers.IO) {

        val fcmToken = getFcmToken()
        if (fcmToken == null) {
            sessionManager.setAuthStateError(errorMessage = "Couldn't get FCM token", authStep = AuthStep.REGISTER)
            return@launch
        }


        val keyPair = withContext(Dispatchers.Default){
            KeyUtil.generateKeypairAsPrior()
        }

        val signUpResponse = sessionManager.signUp(username, password, fcmToken, keyPair.public)
        if(signUpResponse is Resource.Success){
            getTokenAndUpdate(username, password, fcmToken, keyPair)
        }else if(signUpResponse is Resource.GenericError){
            sessionManager.setAuthStateError(signUpResponse.errorMessage,signUpResponse.exception, AuthStep.REGISTER)
        }

    }

    private suspend fun getTokenAndUpdate(username: String, password: String, fcmToken: String, keyPair: KeyPair) {

        val tokenResponse = sessionManager.getToken(username, password)
        if(tokenResponse is Resource.Success){
            val user = UserLocal(
                username,
                keyPair.public,
                keyPair.private,
                fcmToken,
                tokenResponse.value.apply {
                    refreshTokenSetTime = System.currentTimeMillis()
                    accessTokenSetTime = System.currentTimeMillis()
                }
            )
            sessionManager.saveUser(user)
            sessionManager.setAuthStateSuccess(user)

        }else if(tokenResponse is Resource.GenericError){
            sessionManager.setAuthStateError(tokenResponse.errorMessage,tokenResponse.exception, AuthStep.REGISTER)
        }
    }

    private suspend fun getFcmToken(): String? {
        return FirebaseInstanceId.getInstance().instanceId.awaitTask()?.token
    }


}