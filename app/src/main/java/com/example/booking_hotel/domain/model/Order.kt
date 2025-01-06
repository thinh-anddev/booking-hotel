package com.example.booking_hotel.domain.model

data class Order(
    val id: Long?= null,
    val hotelId: Long,
    val userId: Long,
    val orderCode: String,
    val orderName: String,
    val orderContact: String,
    val orderEmail: String,
    val orderStatus: String,
    val numberPeople: Int,
    val totalPrice: Double,
    val dateCreated: String
)