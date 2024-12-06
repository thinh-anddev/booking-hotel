package com.example.booking_hotel.domain.model

import java.io.Serializable

data class Transportation(
    val id: Long?,
    val type: String?,
    val duration: String?
): Serializable