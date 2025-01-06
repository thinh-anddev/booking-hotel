package com.example.booking_hotel.data.remote.dto

import com.example.booking_hotel.domain.model.Order

data class GetListOrderResponse(
    val message: String,
    val orderList: List<Order>
)
