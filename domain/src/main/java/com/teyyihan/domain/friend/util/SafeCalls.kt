package com.teyyihan.domain.friend.util

import com.teyyihan.data.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <T> safeApiCall(
    sessionManager: SessionManager,
    apiCall: suspend (String) -> T?
): Resource<T?> {
    return withContext(Dispatchers.IO) {
        val userCache = sessionManager.getUserCache()
        if(userCache != null){
            try {
                if(userCache.isRefreshTokenExpired()){
                    sessionManager.refreshAccessToken(userCache)
                }
                val result = apiCall.invoke("Bearer ${sessionManager.getUserCache()?.token?.access_token}")
                Resource.Success(result)
            }catch (e: Exception){
                Resource.GenericError(e)
            }
        }else{
            Resource.GenericError(errorMessage = "No cached user")
        }

    }
}