package com.example.booking_hotel.data.remote.dto

import com.example.booking_hotel.domain.model.Brand
import com.example.booking_hotel.domain.model.Hotel

data class HotelResponse(
    val properties: List<Hotel>
)