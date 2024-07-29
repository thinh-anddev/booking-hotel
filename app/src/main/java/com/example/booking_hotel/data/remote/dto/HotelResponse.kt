package com.example.booking_hotel.data.remote.dto

import com.example.booking_hotel.domain.model.Brand
import com.example.booking_hotel.domain.model.Property
import com.google.gson.annotations.SerializedName

data class HotelResponse(
    val brands: List<Brand>,
    val properties: List<Property>,
)