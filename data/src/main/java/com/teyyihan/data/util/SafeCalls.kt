package com.teyyihan.data.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import retrofit2.HttpException
import java.io.IOException
import java.util.*

const val NETWORK_TIMEOUT = 6000L
const val CACHE_TIMEOUT = 6000L

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> T?
): Resource<T?> {
    return withContext(dispatcher) {
        try {
            // throws TimeoutCancellationException
            withTimeout(NETWORK_TIMEOUT){
                Resource.Success(apiCall.invoke())
            }
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            when (throwable) {
                is TimeoutCancellationException -> {
                    val code = 408 // timeout error code
                    Resource.GenericError(code, "NETWORK_ERROR_TIMEOUT")
                }
                is IOException -> {
                    Resource.NetworkError
                }
                is HttpException -> {
                    val code = throwable.code()
                    Resource.GenericError(
                        code,
                        throwable.message()
                    )
                }
                else -> {
                    Resource.GenericError(
                        null,
                        "NETWORK_ERROR_UNKNOWN"
                    )
                }
            }
        }
    }
}

suspend fun <T> safeCacheCall(
    dispatcher: CoroutineDispatcher,
    cacheCall: suspend () -> T?
): Resource<T?> {
    return withContext(dispatcher) {
        try {
            // throws TimeoutCancellationException
            withTimeout(CACHE_TIMEOUT){
                Resource.Success(cacheCall.invoke())
            }
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            when (throwable) {

                is TimeoutCancellationException -> {
                    Resource.GenericError(errorMessage = "CACHE_ERROR_TIMEOUT")
                }
                else -> {
                    Resource.GenericError(errorMessage = "CACHE_ERROR_UNKNOWN")
                }
            }
        }
    }
}
