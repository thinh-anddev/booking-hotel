package com.example.booking_hotel.data.remote

import com.example.booking_hotel.domain.model.User
import com.example.booking_hotel.helper.Constant
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface UserAPI {
    @POST("${Constant.AUTH}/${Constant.REGISTER}")
    suspend fun registerUser(
        @Body user: User
    ): Response<String>

    @POST("${Constant.AUTH}/${Constant.LOGIN}")
    suspend fun login(
        @Query("username") username: String,
        @Query("password") password: String
    ): Response<String>
}