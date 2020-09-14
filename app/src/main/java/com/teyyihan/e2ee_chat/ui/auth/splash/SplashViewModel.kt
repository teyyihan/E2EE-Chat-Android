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

    init {
        checkSession()
    }

    fun init() {
    }

    private fun checkSession() = viewModelScope.launch {
        sessionManager.setAuthStateLoading()

        val userLocal = sessionManager.getUserCache()
        if (userLocal != null) {
            handleCachedUser(userLocal)
        } else {
            sessionManager.setAuthStateError(errorMessage = "No cached user", authStep = AuthStep.CACHE)
        }
    }

    private suspend fun handleCachedUser(userLocal: UserLocal) {
        if (userLocal.isRefreshTokenExpired()) {
            handleExpiredSession()
        } else {
            refreshToken(userLocal)
        }
    }

    private suspend fun refreshToken(userLocal: UserLocal) {
        val refreshedUser = sessionManager.refreshAccessToken(userLocal)
        if (refreshedUser != null) {
            sessionManager.setAuthStateSuccess(refreshedUser)
        } else {
            sessionManager.setAuthStateError(errorMessage = "Failed to refresh token", authStep = AuthStep.CACHE)
        }

    }

    private fun handleExpiredSession() {
        sessionManager.clearUserCache()
        sessionManager.setAuthStateError(errorMessage = "Expired refresh token", authStep = AuthStep.CACHE)
    }


}