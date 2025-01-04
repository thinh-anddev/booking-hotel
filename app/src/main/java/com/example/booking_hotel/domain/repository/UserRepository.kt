package com.example.booking_hotel.domain.repository

import com.example.booking_hotel.data.remote.dto.GetUserResponse
import com.example.booking_hotel.data.remote.dto.LoginResponse
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
}