package com.example.booking_hotel.data.remote.dto

import com.example.booking_hotel.domain.model.Order

data class GetListOrderResponse(
    private val message: String,
    private val orderList: List<Order>
)
