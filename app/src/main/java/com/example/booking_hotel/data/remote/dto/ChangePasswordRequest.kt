package com.example.booking_hotel.data.remote.dto

data class ChangePasswordRequest(
    val password: String,
    val confirmPassword: String,
    val newPassword: String
)
