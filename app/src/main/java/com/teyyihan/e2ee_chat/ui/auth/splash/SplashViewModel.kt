package com.teyyihan.e2ee_chat.ui.auth.splash

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teyyihan.data.model.UserLocal
import com.teyyihan.domain.friend.util.AuthStep
import com.teyyihan.domain.friend.util.SessionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashViewModel @ViewModelInject constructor(
    private val sessionManager: SessionManager
) : ViewModel() {

    private val TAG = "teooo SplashViewModel"

    init {
        checkSession()
    }

    fun init() {
    }

    private fun checkSession() = viewModelScope.launch(Dispatchers.Main) {

        sessionManager.setAuthStateLoading()

        val userCache = sessionManager.getUserCache()
        if(userCache != null){
            handleCachedUser(userCache)
        }else{
            sessionManager.setAuthStateError(errorMessage = "No cached user", authStep = AuthStep.CACHE)
        }

    }

    private suspend fun handleCachedUser(userCache: UserLocal) {
        if(userCache.isRefreshTokenExpired()){
            handleExpiredSession()
        }else{
            refreshToken(userCache)
        }
    }

    private suspend fun refreshToken(userCache: UserLocal) {
        val refreshUser = sessionManager.refreshAccessToken(userCache)
        if(refreshUser != null){
            sessionManager.setAuthStateSuccess(refreshUser)
        }else{
            sessionManager.setAuthStateError(errorMessage = "Failed to refresh token", authStep = AuthStep.CACHE)
        }
    }

    private suspend fun handleExpiredSession() {
        sessionManager.clearUserCache()
        sessionManager.setAuthStateError(errorMessage = "Expired refresh token", authStep = AuthStep.CACHE)
    }

}