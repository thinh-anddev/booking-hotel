package com.example.booking_hotel.domain.model

data class Price(
    val logo: String,
    val num_guests: Int,
    val rate_per_night: RatePerNightX,
    val source: String
)