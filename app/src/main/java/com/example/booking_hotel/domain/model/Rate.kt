package com.example.booking_hotel.domain.model

data class Rate(
    val id: Long?,
    val lowest: String?,
    val extractedLowest: Int?,
    val beforeTaxesFees: String?,
    val extractedBeforeTaxesFees: Int?
)