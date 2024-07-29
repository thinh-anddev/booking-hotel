package com.example.booking_hotel.domain.model

data class SearchParameters(
    val adults: Int,
    val check_in_date: String,
    val check_out_date: String,
    val children: Int,
    val currency: String,
    val engine: String,
    val gl: String,
    val hl: String,
    val q: String
)