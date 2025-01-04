package com.example.booking_hotel.data.repository

import com.example.booking_hotel.data.remote.UserAPI
import com.example.booking_hotel.domain.model.User
import com.example.booking_hotel.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userAPI: UserAPI
): UserRepository {
    override suspend fun registerUser(user: User): String {
        return try {
            val response = userAPI.registerUser(user)
            if (response.isSuccessful) {
                response.body() ?: "Registration successful"
            } else {
                "Registration failed: ${response.message()}"
            }
        } catch (e: Exception) {
            "Error: ${e.message}"
        }
    }

    override suspend fun login(username: String, password: String): String {
        return try {
            val response = userAPI.login(username = username, password = password)
            if (response.isSuccessful) {
                response.body() ?: "Login successful"
            } else {
                "Login failed: ${response.message()}"
            }
        } catch (e: Exception) {
            "Error: ${e.message}"
        }
    }
}