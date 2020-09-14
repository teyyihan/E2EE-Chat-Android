package com.teyyihan.e2ee_chat.ui.auth.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.google.firebase.iid.FirebaseInstanceId
import com.teyyihan.core.util.*
import com.teyyihan.data.model.UserLocal
import com.teyyihan.data.model.request.UpdateRequest
import com.teyyihan.data.model.response.TokenResponse
import com.teyyihan.data.util.Resource
import com.teyyihan.e2ee_chat.util.awaitTask
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.security.KeyPair

class LoginViewModel @ViewModelInject constructor(
    private val sessionManager: SessionManager
) : ViewModel() {

    private val TAG = "teooo LoginViewModel"

    private val keyPair = KeyUtil.generateKeys()


    fun loginAttempt(username: String, password: String) = viewModelScope.launch(Dispatchers.IO) {
        setStatus(AuthState.Loading)

        val tokenResponse = sessionManager.getToken(username, password)
        if(tokenResponse is Resource.Success){

            /**
             *  Updates both locally and remotely
             */
            updateMe(tokenResponse, username)

        }else if(tokenResponse is Resource.GenericError){
            setAuthStateError("Couldn't get tokens")
        }
    }

    private suspend fun updateMe(
        tokenResponse: Resource.Success<TokenResponse>,
        username: String
    ) {

        val fcmToken = getFcmToken()
        if (fcmToken == null) {
            setAuthStateError("Couldn't get FCM token")
            return
        }

        val updateServerResponse = updateMeOnServer(tokenResponse.value.access_token, fcmToken, keyPair)
        if (updateServerResponse is Resource.GenericError) {
            setAuthStateError("Couldn't update you on server")
            return
        }

        updateMeLocally(tokenResponse, username, fcmToken)
    }

    private suspend fun updateMeLocally(
        tokenResponse: Resource.Success<TokenResponse>,
        username: String,
        fcmToken: String
    ) {
        val tokenResponseValue = tokenResponse.value.apply {
            refreshTokenSetTime = System.currentTimeMillis()
            accessTokenSetTime = System.currentTimeMillis()
        }
        val user = UserLocal(
            username,
            keyPair.public,
            keyPair.private,
            fcmToken,
            tokenResponseValue
        )

        withContext(Dispatchers.Main) {
            updateMeOnCache(user)
        }

        setAuthStateSuccess(user)
    }


    private fun setAuthStateSuccess(value: UserLocal){
        sessionManager.setAuthState(AuthState.Success(Event(value)))
    }

    private fun setAuthStateError(errorMessage: String, e: Exception? = null) {
        sessionManager.setAuthState(AuthState.Error(Event(
            AuthErrorModel(
                errorMessage,
                e,
                AuthStep.LOGIN
            )
        )))
    }

    private fun updateMeOnCache(
        userLocal: UserLocal
    ) = sessionManager.saveUser(userLocal)

    private suspend fun updateMeOnServer(
        accessToken: String,
        fcmToken: String,
        keyPair: KeyPair
    ) = sessionManager.updateMeOnServer(
        accessToken,
        UpdateRequest(keyPair.public.convertToString(), fcmToken)
    )

    private suspend fun getFcmToken(): String? {
        return FirebaseInstanceId.getInstance().instanceId.awaitTask()?.token
    }


    private suspend fun setStatus(status: AuthState<UserLocal>) = withContext(Dispatchers.Main) {
        sessionManager.setAuthState(status)
    }


}