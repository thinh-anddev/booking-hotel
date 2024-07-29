package com.example.booking_hotel.domain.model

data class TotalRate(
    val before_taxes_fees: String,
    val extracted_before_taxes_fees: Int,
    val extracted_lowest: Int,
    val lowest: String
)