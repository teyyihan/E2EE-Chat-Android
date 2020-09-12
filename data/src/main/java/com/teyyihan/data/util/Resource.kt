package com.teyyihan.data.util


sealed class Resource<out T> {

    data class Success<out T>(val value: T): Resource<T>()

    data class GenericError(
        val exception: Throwable? = null,
        val errorMessage: String? = null
    ): Resource<Nothing>()

    object Loading: Resource<Nothing>()

    object NothingRN: Resource<Nothing>()
}
