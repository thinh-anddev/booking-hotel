package com.example.booking_hotel.domain.model

data class User(
    val id: Long?= null,
    val userName: String,
    val password: String,
    val contact: String,
    val email: String,
    val age: Int,
    val avatar: String,
    val dateCreated: String
)