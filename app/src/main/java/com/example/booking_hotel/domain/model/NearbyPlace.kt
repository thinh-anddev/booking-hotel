package com.example.booking_hotel.domain.model

import java.io.Serializable

data class NearbyPlace(
    val id: Long?,
    val name: String?,
    val transportations: List<Transportation>?
): Serializable