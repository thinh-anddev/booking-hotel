package com.example.booking_hotel.domain.model

data class ReviewBreakdown(
    val id: Long?,
    val name: String?,
    val description: String?,
    val totalMentioned: Int?,
    val positive: Int?,
    val negative: Int?,
    val neutral: Int?
)