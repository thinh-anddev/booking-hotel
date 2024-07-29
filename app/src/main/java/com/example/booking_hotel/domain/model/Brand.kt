package com.example.booking_hotel.domain.model

data class Brand(
    val children: List<Children>,
    val id: Int,
    val name: String
)