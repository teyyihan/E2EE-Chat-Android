package com.teyyihan.core.util

import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import androidx.lifecycle.*
import com.squareup.moshi.JsonAdapter
import com.teyyihan.core.Consts
import com.teyyihan.data.model.UserLocal
import com.teyyihan.data.model.request.UpdateRequest
import com.teyyihan.data.model.response.TokenResponse
import com.teyyihan.data.remote.abstraction.AuthRemoteDataSource
import com.teyyihan.data.remote.implementation.ResourceAPI
import com.teyyihan.data.remote.implementation.TokenAPI
import com.teyyihan.data.util.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val encryptedSharedPreferences: SharedPreferences,
    private val userAdapter: JsonAdapter<UserLocal>
) {
    private val TAG = "teooo SessionManager"

    private val _authState: MutableLiveData<AuthState<UserLocal>> = MutableLiveData(AuthState.NothingRN)
    val authState: LiveData<AuthState<UserLocal>>
        get() = _authState


    fun setAuthState(state: AuthState<UserLocal>){
        _authState.postValue(state)
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

    fun saveUser(userLocal: UserLocal) {
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

}







//@Singleton
//class SessionManager @Inject constructor(
//    private val resourceAPI: ResourceAPI,
//    private val tokenAPI: TokenAPI,
//    private val encryptedSharedPreferences: SharedPreferences,
//    private val userAdapter: JsonAdapter<UserLocal>
//) {
//    private val TAG = "teooo SessionManager"
//
//    private val _authState: MutableLiveData<AuthState<UserLocal>> = MutableLiveData(AuthState.NothingRN)
//    val authState: LiveData<AuthState<UserLocal>>
//        get() = _authState
//
//
//    fun setAuthState(state: AuthState<UserLocal>){
//        _authState.postValue(state)
//    }
//
//    fun getUserCache(): UserLocal? {
//        val userStr = encryptedSharedPreferences.getString(Consts.USER_SP, null)
//        return userStr?.let { userAdapter.fromJson(it) }
//    }
//
//
//    fun clearUserCache() {
//        encryptedSharedPreferences.edit(commit = true) {
//            putString(Consts.USER_SP, null)
//        }
//    }
//
//    fun saveUser(userLocal: UserLocal) {
//        encryptedSharedPreferences.edit(commit = true) {
//            putString(Consts.USER_SP, userAdapter.toJson(userLocal))
//        }
//    }
//
//    suspend fun refreshAccessToken(userLocal: UserLocal): UserLocal? {
//        return try {
//            val newToken = tokenAPI.refreshAccessToken(userLocal.token.refresh_token)
//            val updatedUser = userLocal.apply {
//                token.access_token = newToken.access_token
//                token.accessTokenSetTime = System.currentTimeMillis()
//            }
//            saveUser(updatedUser)
//            updatedUser
//        } catch (e: Exception) {
//            null
//        }
//    }
//
//    suspend fun getToken(username: String, password: String): Resource<TokenResponse> {
//        return try {
//            val tokenResponse = tokenAPI.token(username, password)
//            Resource.Success(tokenResponse)
//        } catch (e: Exception) {
//            Resource.GenericError(errorMessage = e.localizedMessage)
//        }
//    }
//
//    suspend fun updateMeOnServer(
//        accessToken: String,
//        updateRequest: UpdateRequest
//    ): Resource<Boolean> {
//        return try {
//            resourceAPI.updateMe(
//                "Bearer $accessToken",
//                UpdateRequest(updateRequest.publicKey, updateRequest.fcmToken)
//            )
//            Resource.Success(true)
//        } catch (e: Exception) {
//            Log.d(TAG, "updateMeOnServer: $e")
//            Resource.GenericError(errorMessage = e.localizedMessage)
//        }
//    }
//
//}