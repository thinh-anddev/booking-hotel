package com.example.booking_hotel.data.remote.dto

import com.example.booking_hotel.domain.model.User

data class GetUserResponse(
    val message: String,
    val user: User?
)
