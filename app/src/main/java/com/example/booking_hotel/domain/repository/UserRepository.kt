package com.example.booking_hotel.domain.repository

import com.example.booking_hotel.domain.model.User

interface UserRepository {
    suspend fun registerUser(user: User): String
    suspend fun login(
        username: String,
        password: String
    ): String
}