package com.example.booking_hotel.data.remote.dto

data class UpdateUserForAdminRequest(
    val userName: String,
    val password: String,
    val email: String,
    val age: Int,
    val avatar: String,
    val contact: String
)
