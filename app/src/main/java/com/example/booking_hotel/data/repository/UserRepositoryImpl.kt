package com.example.booking_hotel.data.repository

import android.util.Log
import androidx.navigation.NavController
import com.example.booking_hotel.data.remote.UserAPI
import com.example.booking_hotel.data.remote.dto.GetUserResponse
import com.example.booking_hotel.data.remote.dto.LoginResponse
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
    @Override
    override suspend fun login(username: String, password: String): LoginResponse? {
        val response = userAPI.login(username = username, password = password)
        return if (response.isSuccessful) {
            response.body()
        } else {
            val errorBody = response.errorBody()?.string()
            throw Exception(errorBody ?: "Unknown error occurred")
        }
    }

    override suspend fun getUserById(id: Long): GetUserResponse? {
        val response = userAPI.getUserById(id)
        return if (response.isSuccessful) {
            response.body()
        } else {
            val errorBody = response.errorBody()?.string()
            throw Exception(errorBody ?: "Unknown error occurred")
        }
    }
}