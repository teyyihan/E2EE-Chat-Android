package com.teyyihan.domain.friend.util

data class AuthErrorModel(
    val errorMessage: String?,
    val exception: Exception? = null,
    val where: AuthStep
)

enum class AuthStep{
    CACHE,
    LOGIN,
    REGISTER
}