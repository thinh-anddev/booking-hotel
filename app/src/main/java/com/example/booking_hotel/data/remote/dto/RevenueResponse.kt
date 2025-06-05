package com.example.booking_hotel.data.remote.dto

data class RevenueResponse(
    val year:Int,
    val hotelId:Long,
    val month:Int,
    val totalRevenue:Double
)
