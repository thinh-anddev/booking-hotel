package com.example.booking_hotel.domain.model

data class SerpapiPagination(
    val current_from: Int,
    val current_to: Int,
    val next: String,
    val next_page_token: String
)