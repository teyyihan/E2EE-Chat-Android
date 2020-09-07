package com.teyyihan.data.model.response

import com.squareup.moshi.Json


data class TokenResponse(
    @Json(name="access_token")
    val access_token: String,

    @Json(name="expires_in")
    val expires_in: Int,

    @Json(name="refresh_expires_in")
    val refresh_expires_in: Int,

    @Json(name="refresh_token")
    val refresh_token: String,

    @Json(name="token_type")
    val token_type: String,

    val time: Long = System.currentTimeMillis()
)