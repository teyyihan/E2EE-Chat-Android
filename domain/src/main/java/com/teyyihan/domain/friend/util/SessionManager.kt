package com.teyyihan.domain.friend.util

import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.squareup.moshi.JsonAdapter
import com.teyyihan.data.model.UserLocal
import com.teyyihan.data.model.request.SignUpRequest
import com.teyyihan.data.model.request.UpdateRequest
import com.teyyihan.data.model.response.SignUpResponse
import com.teyyihan.data.model.response.TokenResponse
import com.teyyihan.data.remote.abstraction.AuthRemoteDataSource
import com.teyyihan.data.util.Resource
import com.teyyihan.domain.friend.Consts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val encryptedSharedPreferences: SharedPreferences,
    private val userAdapter: JsonAdapter<UserLocal>
) {
    private val TAG = "teooo SessionManager"

    private val _authState: MutableLiveData<AuthState<UserLocal>> =
        MutableLiveData(AuthState.NothingRN)
    val authState: LiveData<AuthState<UserLocal>>
        get() = _authState

    fun getUser(): UserLocal? {
        return try {
            (_authState.value as AuthState.Success).value.peekContent()
        }catch (e: Exception){
            null
        }
    }

    fun getUserCache(): UserLocal? {
        val userStr = encryptedSharedPreferences.getString(Consts.USER_SP, null)
        return userStr?.let { userAdapter.fromJson(it) }
    }


    fun clearUserCache() {
        encryptedSharedPreferences.edit(commit = true) {
            putString(Consts.USER_SP, null)
        }
    }

    suspend fun saveUser(userLocal: UserLocal) = withContext(Dispatchers.Main){
        encryptedSharedPreferences.edit(commit = true) {
            putString(Consts.USER_SP, userAdapter.toJson(userLocal))
        }
    }

    suspend fun refreshAccessToken(userLocal: UserLocal): UserLocal? {
       return try {
            val newToken = authRemoteDataSource.refreshAccessToken(userLocal.token.refresh_token)
            val updatedUser = userLocal.apply {
                token.access_token = newToken.access_token
                token.accessTokenSetTime = System.currentTimeMillis()
            }
            saveUser(updatedUser)
            updatedUser
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getToken(username: String, password: String): Resource<TokenResponse> {
        return try {
            val tokenResponse = authRemoteDataSource.getToken(username, password)
            Resource.Success(tokenResponse)
        } catch (e: Exception) {
            Resource.GenericError(errorMessage = e.localizedMessage)
        }
    }

    suspend fun updateMeOnServer(
        accessToken: String,
        updateRequest: UpdateRequest
    ): Resource<Boolean> {
        return try {
            authRemoteDataSource.updateMe(
                "Bearer $accessToken",
                UpdateRequest(updateRequest.publicKey, updateRequest.fcmToken)
            )
            Resource.Success(true)
        } catch (e: Exception) {
            Log.d(TAG, "updateMeOnServer: $e")
            Resource.GenericError(errorMessage = e.localizedMessage)
        }
    }

    suspend fun signUp(
        username: String,
        password: String,
        fcmToken: String,
        publicKey: String
    ): Resource<SignUpResponse> {
        return try {
            val signUpResponse = authRemoteDataSource.signUp(
                SignUpRequest(
                    username, password, fcmToken, publicKey
                )
            )
            Resource.Success(signUpResponse)
        } catch (e: Exception) {
            Resource.GenericError(e, e.localizedMessage)
        }
    }

    suspend fun setAuthStateLoading() = withContext(Dispatchers.Main) {
        _authState.value = AuthState.Loading
    }

    suspend fun setAuthStateSuccess(value: UserLocal) = withContext(Dispatchers.Main) {
        _authState.value = AuthState.Success(Event(value))
    }

    suspend fun setAuthStateError(errorMessage: String?, e: Exception? = null, authStep: AuthStep) =
        withContext(Dispatchers.Main) {
            _authState.postValue(
                AuthState.Error(
                    Event(
                        AuthErrorModel(
                            errorMessage,
                            e,
                            authStep
                        )
                    )
                )
            )
        }

}