package com.teyyihan.data.remote.implementation

import com.teyyihan.data.model.Friend
import retrofit2.http.GET
import retrofit2.http.Query

interface FriendRemoteAPI {

    @GET("users")
    suspend fun getUserFromBackend(
        @Query("phoneNum") phoneNum: String
    ): Friend

}