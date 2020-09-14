package com.teyyihan.e2ee_chat.ui.auth.splash

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teyyihan.core.util.*
import com.teyyihan.data.model.UserLocal
import kotlinx.coroutines.launch
import java.lang.Exception

class SplashViewModel @ViewModelInject constructor(
    private val sessionManager: SessionManager
) : ViewModel() {

    private val TAG = "teooo SplashViewModel"

    init {
        checkSession()
    }

    fun init() {
    }

    private fun checkSession() = viewModelScope.launch {
        sessionManager.setAuthState(AuthState.Loading)

        val userLocal = sessionManager.getUserCache()
        if (userLocal != null) {
            Log.d(TAG, "checkSession: cached user $userLocal")
            handleCachedUser(userLocal)
        } else {
            Log.d(TAG, "checkSession: no cached user")
            setAuthStateError("No cached user")
        }
    }

    private suspend fun handleCachedUser(userLocal: UserLocal) {
        if (userLocal.isRefreshTokenExpired()) {
            Log.d(TAG, "handleCachedUser: expired")
            handleExpiredSession()
        } else {
            Log.d(TAG, "handleCachedUser: valid")
            refreshToken(userLocal)
        }
    }

    private suspend fun refreshToken(userLocal: UserLocal) {
        val refreshedUser = sessionManager.refreshAccessToken(userLocal)
        if (refreshedUser != null) {
            Log.d(TAG, "refreshToken: refreshed token")
            setAuthStateSuccess(refreshedUser)
        } else {
            Log.d(TAG, "refreshToken: failed to refresh token")
            setAuthStateError("Failed to refresh token")
        }

    }

    private fun handleExpiredSession() {
        Log.d(TAG, "handleExpiredSession: clear cache")
        sessionManager.clearUserCache()
        setAuthStateError("Expired refresh token")
    }

    private fun setAuthStateSuccess(value: UserLocal){
        sessionManager.setAuthState(AuthState.Success(Event(value)))
    }

    private fun setAuthStateError(errorMessage: String, e: Exception? = null) {
        sessionManager.setAuthState(AuthState.Error(Event(
            AuthErrorModel(
                errorMessage,
                e,
                AuthStep.CACHE
            )
        )))
    }

}