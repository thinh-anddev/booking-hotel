package com.example.booking_hotel.data.remote

import com.example.booking_hotel.data.remote.dto.HotelResponse
import com.example.booking_hotel.domain.model.Hotel
import com.example.booking_hotel.helper.Constant
import retrofit2.http.GET
import retrofit2.http.Query

interface HotelAPI {
    @GET(Constant.API_GET_HOTEL)
    suspend fun getHotels(
        @Query("query") query: String
    ): List<Hotel>
}