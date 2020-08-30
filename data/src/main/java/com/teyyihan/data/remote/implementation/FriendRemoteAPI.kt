package com.teyyihan.data.remote.implementation

import com.teyyihan.data.model.entity.Friend
import retrofit2.http.GET
import retrofit2.http.Query

interface FriendRemoteAPI {

    @GET("users")
    suspend fun getUserByID(
        @Query("userid") userid: String
    ): Friend

    @GET("users")
    suspend fun getUserByPhoneNumber(
        @Query("phone") phoneNum: String
    ): Friend

}