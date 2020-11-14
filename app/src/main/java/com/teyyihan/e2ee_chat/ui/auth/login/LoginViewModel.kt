package com.teyyihan.e2ee_chat.ui.auth.login

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.google.firebase.iid.FirebaseInstanceId
import com.teyyihan.data.model.UserLocal
import com.teyyihan.data.model.request.UpdateRequest
import com.teyyihan.data.model.response.TokenResponse
import com.teyyihan.data.util.KeyUtil
import com.teyyihan.data.util.Resource
import com.teyyihan.data.util.convertToString
import com.teyyihan.domain.friend.util.AuthStep
import com.teyyihan.domain.friend.util.SessionManager
import com.teyyihan.e2ee_chat.util.awaitTask
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.security.KeyPair

class LoginViewModel @ViewModelInject constructor(
    private val sessionManager: SessionManager
) : ViewModel() {

    private val TAG = "teooo LoginViewModel"


    fun loginAttempt(username: String, password: String) = viewModelScope.launch(Dispatchers.IO) {
        Log.d(TAG, "loginAttempt: 11111111111111")
        sessionManager.setAuthStateLoading()
        Log.d(TAG, "loginAttempt: 222222222222")

        val tokenResponse = sessionManager.getToken(username, password)
        Log.d(TAG, "loginAttempt: 3333333333333")

        if(tokenResponse is Resource.Success){
            updateMe(tokenResponse, username)
        }else if(tokenResponse is Resource.GenericError){
            sessionManager.setAuthStateError(errorMessage = "Couldn't get tokens", authStep = AuthStep.LOGIN)
        }
    }

    private suspend fun updateMe(
        tokenResponse: Resource.Success<TokenResponse>,
        username: String
    ) {
        Log.d(TAG, "updateMe: 5555555555555555")

        val keyPair = KeyUtil.generateKeypairAsPrior()

        Log.d(TAG, "updateMe: 66666666666666")

        val fcmToken = FirebaseInstanceId.getInstance().instanceId.awaitTask()?.token
        if (fcmToken == null) {
            sessionManager.setAuthStateError(errorMessage = "Couldn't get FCM token", authStep = AuthStep.LOGIN)
            return
        }

        val updateServerResponse = updateMeOnServer(tokenResponse.value.access_token, fcmToken, keyPair)
        if (updateServerResponse is Resource.GenericError) {
            sessionManager.setAuthStateError(errorMessage = "Couldn't update you on server", authStep = AuthStep.LOGIN)
            return
        }

        updateMeLocally(tokenResponse, username, fcmToken,keyPair)
    }

    private suspend fun updateMeLocally(
        tokenResponse: Resource.Success<TokenResponse>,
        username: String,
        fcmToken: String,
        keyPair: KeyPair
    ) {
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
    }



    private suspend fun updateMeOnServer(
        accessToken: String,
        fcmToken: String,
        keyPair: KeyPair
    ) = sessionManager.updateMeOnServer(
        accessToken,
        UpdateRequest(keyPair.public.convertToString(), fcmToken)
    )


}