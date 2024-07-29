package com.example.booking_hotel.domain.model

data class ReviewsBreakdown(
    val description: String,
    val name: String,
    val negative: Int,
    val neutral: Int,
    val positive: Int,
    val total_mentioned: Int
)