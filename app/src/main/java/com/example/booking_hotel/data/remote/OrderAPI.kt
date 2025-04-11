package com.example.booking_hotel.data.remote

import com.example.booking_hotel.data.remote.dto.GetListOrderResponse
import com.example.booking_hotel.domain.model.Order
import com.example.booking_hotel.helper.Constant
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface OrderAPI {
    @POST("${Constant.ORDER}/${Constant.SAVE_ORDER}")
    suspend fun saveOrder(
        @Body order: Order
    ): Response<String>
    @GET("${Constant.ORDER}/${Constant.GET_ORDER}/{id}")
    suspend fun getOrderById(
        @Path("id") id: Long
    ): Response<Order>
    @GET("${Constant.ORDER}/${Constant.GET_LIST_ORDER}/{id}")
    suspend fun getListOrder(
        @Path("id") id: Long
    ): Response<GetListOrderResponse>
    @PUT("${Constant.ORDER}/${Constant.UPDATE_ORDER_STATUS}/{id}")
    suspend fun updateOrderStatus(
        @Path("id") id: Long,
        @Query("status") status: String
    ): Response<String>
}