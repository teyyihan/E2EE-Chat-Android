package com.teyyihan.e2ee_chat.ui.auth.register

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.iid.FirebaseInstanceId
import com.teyyihan.core.util.*
import com.teyyihan.data.model.UserLocal
import com.teyyihan.data.util.Resource
import com.teyyihan.e2ee_chat.util.awaitTask
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel @ViewModelInject constructor(
    private val sessionManager: SessionManager
) : ViewModel() {



    private val keyPair = KeyUtil.generateKeys()

    fun registerFlow(username: String, password: String) = viewModelScope.launch(Dispatchers.IO) {

        val fcmToken = getFcmToken()
        if (fcmToken == null) {
            setAuthStateError("Couldn't get FCM token")
            return@launch
        }

        val publicKeyStr = keyPair.public.convertToString()
        if (publicKeyStr == null) {
            setAuthStateError("Something went wrong with public key")
            return@launch
        }


        val signUpResponse = sessionManager.signUp(username, password, fcmToken, publicKeyStr)
        if(signUpResponse is Resource.Success){

            getTokenAndUpdate(username, password, fcmToken)

        }else if(signUpResponse is Resource.GenericError){
            setAuthStateError(signUpResponse.errorMessage, signUpResponse.exception)
        }

    }

    private suspend fun getTokenAndUpdate(username: String, password: String, fcmToken: String) {
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
            updateMeOnCache(user)
            setAuthStateSuccess(user)

        }else if(tokenResponse is Resource.GenericError){
            setAuthStateError(tokenResponse.errorMessage,tokenResponse.exception)
        }
    }

    private suspend fun getFcmToken(): String? {
        return FirebaseInstanceId.getInstance().instanceId.awaitTask()?.token
    }

    private fun updateMeOnCache(
        userLocal: UserLocal
    ) = sessionManager.saveUser(userLocal)


    private fun setAuthStateSuccess(value: UserLocal) {
        sessionManager.setAuthState(AuthState.Success(Event(value)))
    }

    private fun setAuthStateError(errorMessage: String?, e: java.lang.Exception? = null) {
        sessionManager.setAuthState(
            AuthState.Error(
                Event(
                    AuthErrorModel(
                        errorMessage,
                        e,
                        AuthStep.REGISTER
                    )
                )
            )
        )
    }

}