package com.teyyihan.core.util

data class AuthErrorModel(
    val errorMessage: String,
    val exception: Exception? = null,
    val where: AuthStep
)

enum class AuthStep{
    CACHE,
    LOGIN
}