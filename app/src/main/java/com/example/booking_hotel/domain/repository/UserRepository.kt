package com.example.booking_hotel.domain.repository

import com.example.booking_hotel.data.remote.dto.ChangePasswordRequest
import com.example.booking_hotel.data.remote.dto.GetUserResponse
import com.example.booking_hotel.data.remote.dto.LoginResponse
import com.example.booking_hotel.data.remote.dto.SendOTPResponse
import com.example.booking_hotel.data.remote.dto.UpdateUserForAdminRequest
import com.example.booking_hotel.data.remote.dto.UpdateUserRequest
import com.example.booking_hotel.domain.model.User

interface UserRepository {
    suspend fun registerUser(user: User): String
    suspend fun login(
        username: String,
        password: String
    ): LoginResponse?

    suspend fun getUserById(
        id: Long
    ): GetUserResponse?
    suspend fun updateUser(
        id: Long,
        updateUserRequest: UpdateUserRequest
    ): String
    suspend fun changePassword(
        id: Long,
        changePasswordRequest: ChangePasswordRequest
    ): String
    suspend fun forgotPassword(
        email: String
    ): String
    suspend fun sendOTP(
        user: User
    ): SendOTPResponse?
    suspend fun findAll():List<User>
    suspend fun updateUserForAdmin(
        id: Long,
        user: UpdateUserForAdminRequest
    ): String
    suspend fun deleteUser(id: Long): String
}