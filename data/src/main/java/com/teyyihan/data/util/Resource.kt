package com.teyyihan.data.util

import com.teyyihan.data.util.Status.*


sealed class Resource<out T>{

    data class Success<out T>(val data : T) : Resource<T>()

    data class Fail(val error : String) : Resource<String>()

    object Loading : Resource<Nothing>()
}