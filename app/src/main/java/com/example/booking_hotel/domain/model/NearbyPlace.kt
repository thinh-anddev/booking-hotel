package com.example.booking_hotel.domain.model

data class NearbyPlace(
    val id: Long?,
    val name: String?,
    val transportations: List<Transportation>?
)