package com.example.booking_hotel.data.repository

import com.example.booking_hotel.data.remote.OrderAPI
import com.example.booking_hotel.data.remote.dto.GetListOrderResponse
import com.example.booking_hotel.data.remote.dto.HotelStat
import com.example.booking_hotel.data.remote.dto.RevenueResponse
import com.example.booking_hotel.domain.model.Order
import com.example.booking_hotel.domain.repository.OrderRepository

class OrderRepositoryImpl(
    private val orderAPI: OrderAPI
): OrderRepository {
    override suspend fun saveOrder(order: Order): String {
        val response = orderAPI.saveOrder(order)
        return if (response.isSuccessful) {
            response.body() ?: "successful"
        } else {
            "Registration failed: ${response.message()}"
        }
    }

    override suspend fun getOrderById(id: Long): Order? {
        val response = orderAPI.getOrderById(id)
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }

    override suspend fun getListOrder(userId: Long): GetListOrderResponse? {
        return try {
            val response = orderAPI.getListOrder(userId)
            if (response.isSuccessful) {
                response.body()
            } else {
                val errorBody = response.errorBody()?.string() ?: "Unknown error"
                GetListOrderResponse(message = errorBody, orderList = emptyList())
            }
        } catch (e: Exception) {
            val errorMessage = e.message ?: "Unknown exception"
            GetListOrderResponse(message = errorMessage, orderList = emptyList())
        }
    }

    override suspend fun updateOrderStatus(orderId: Long, status: String): String? {
        val response = orderAPI.updateOrderStatus(orderId, status)
        return if (response.isSuccessful) {
            response.body()
        } else {
            val errorBody = response.errorBody()?.string()
            throw Exception(errorBody ?: "Unknown error occurred")
        }
    }

    override suspend fun getAllHotelStat(): List<HotelStat> {
        val response=orderAPI.getAllHotelStat()
        return if(response.isSuccessful){
            response.body()!!
        }else{
            val errorBody = response.errorBody()?.string()
            throw Exception(errorBody ?: "Unknown error occurred")
        }
    }

    override suspend fun getTop10HotelStat(): List<HotelStat> {
        val response=orderAPI.getTop10HotelStat()
        return if(response.isSuccessful){
            response.body()!!
        }else{
            val errorBody = response.errorBody()?.string()
            throw Exception(errorBody ?: "Unknown error occurred")
        }
    }

    override suspend fun getMostBookHotel(): HotelStat {
        val response=orderAPI.getMostBookHotel()
        return if(response.isSuccessful){
            response.body()!!
        }else{
            val errorBody = response.errorBody()?.string()
            throw Exception(errorBody ?: "Unknown error occurred")
        }
    }

    override suspend fun getMonthlyRevenue(year: Int, hotelId: Long, month: Int): RevenueResponse {
        val response=orderAPI.getMonthlyRevenue(hotelId, month, year)
        return if(response.isSuccessful){
            response.body()!!
        }else{
            val errorBody = response.errorBody()?.string()
            throw Exception(errorBody ?: "Unknown error occurred")
        }
    }
}