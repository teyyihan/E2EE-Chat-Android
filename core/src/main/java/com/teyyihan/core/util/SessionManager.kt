package com.teyyihan.core.util

import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.squareup.moshi.JsonAdapter
import com.teyyihan.core.Consts
import com.teyyihan.data.model.UserLocal
import com.teyyihan.data.util.stringLiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor(
    private val encryptedSharedPreferences: SharedPreferences,
    private val userAdapter: JsonAdapter<UserLocal>
){

    private val _cachedUser: LiveData<String?> = encryptedSharedPreferences.stringLiveData(Consts.USER_SP,null)
    val cachedUser: LiveData<UserLocal?> = Transformations.map(_cachedUser){
        it?.let { userAdapter.fromJson(it) }
    }


    fun getUserFromSP(): UserLocal?{
        val userString = encryptedSharedPreferences.getString(Consts.USER_SP,null)
        return userString?.let { userAdapter.fromJson(userString) }
    }

    fun saveUserToSP(userLocal: UserLocal){
        encryptedSharedPreferences.edit {
            putString(Consts.USER_SP,userAdapter.toJson(userLocal))
        }
    }

}