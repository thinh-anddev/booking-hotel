package com.example.booking_hotel.domain.repository

import com.example.booking_hotel.data.remote.dto.GetListOrderResponse
import com.example.booking_hotel.data.remote.dto.HotelStat
import com.example.booking_hotel.domain.model.Order

interface OrderRepository {
    suspend fun saveOrder(order: Order): String
    suspend fun getOrderById(id: Long): Order?
    suspend fun getListOrder(userId: Long): GetListOrderResponse?
    suspend fun updateOrderStatus(orderId: Long, status: String): String?
    suspend fun getAllHotelStat():List<HotelStat>
    suspend fun getTop10HotelStat():List<HotelStat>
}