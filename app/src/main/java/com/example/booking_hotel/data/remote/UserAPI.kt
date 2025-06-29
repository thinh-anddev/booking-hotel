package com.example.booking_hotel.data.remote

import com.example.booking_hotel.data.remote.dto.ChangePasswordRequest
import com.example.booking_hotel.data.remote.dto.GetUserResponse
import com.example.booking_hotel.data.remote.dto.LoginResponse
import com.example.booking_hotel.data.remote.dto.SendOTPResponse
import com.example.booking_hotel.data.remote.dto.UpdateUserForAdminRequest
import com.example.booking_hotel.data.remote.dto.UpdateUserRequest
import com.example.booking_hotel.domain.model.User
import com.example.booking_hotel.helper.Constant
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
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
    ): Response<LoginResponse>

    @GET("${Constant.AUTH}/${Constant.GET_USER}/{id}")
    suspend fun getUserById(
        @Path("id") id: Long
    ): Response<GetUserResponse>
    @GET("${Constant.AUTH}/${Constant.FIND_ALL}")
    suspend fun findAll(
    ): Response<List<User>>

    @PUT("${Constant.AUTH}/${Constant.UPDATE_USER}/{id}")
    suspend fun updateUser(
        @Path("id") id: Long,
        @Body updateUserRequest: UpdateUserRequest
    ): Response<String>
    @PUT("${Constant.AUTH}/${Constant.CHANGE_PASSWORD}/{id}")
    suspend fun changePassword(
        @Path("id") id: Long,
        @Body changePasswordRequest: ChangePasswordRequest
    ): Response<String>
    @POST("${Constant.AUTH}/${Constant.FORGOT_PASSWORD}")
    suspend fun forgotPassword(
        @Query("email") email: String
    ): Response<String>
    @POST("${Constant.AUTH}/${Constant.SEND_OTP}")
    suspend fun sendOtp(
        @Body user: User
    ): Response<SendOTPResponse>
    @PUT("${Constant.AUTH}/${Constant.UPDATE_USER_FOR_ADMIN}/{id}")
    suspend fun updateUserForAdmin(
        @Path("id") id: Long,
        @Body user: UpdateUserForAdminRequest
    ): Response<String>
    @DELETE("${Constant.AUTH}/${Constant.DELETE_USER}/{id}")
    suspend fun deleteUser(
        @Path("id") id: Long
    ): Response<String>
}