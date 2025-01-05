package com.example.booking_hotel.domain.model

data class Order(
    val id: Long?= null,
    val hotelId: Long,
    val userId: Long,
    val orderCode: String,
    val orderStatus: String,
    val dateCreated: String
)