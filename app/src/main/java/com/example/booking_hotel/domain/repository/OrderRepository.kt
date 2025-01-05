package com.example.booking_hotel.domain.repository

import com.example.booking_hotel.data.remote.dto.GetListOrderResponse
import com.example.booking_hotel.domain.model.Order

interface OrderRepository {
    suspend fun saveOrder(order: Order): String
    suspend fun getOrderById(id: Long): Order?
    suspend fun getListOrder(userId: Long): GetListOrderResponse?
    suspend fun updateOrderStatus(orderId: Long, status: String): String?
}