package com.example.booking_hotel.data.remote

import com.example.booking_hotel.data.remote.dto.HotelResponse
import com.example.booking_hotel.domain.model.Hotel
import com.example.booking_hotel.domain.model.Rating
import com.example.booking_hotel.helper.Constant
import com.example.booking_hotel.helper.Constant.AVGRATE
import com.example.booking_hotel.helper.Constant.RATINGS
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HotelAPI {
    @GET(Constant.API_GET_HOTEL)
    suspend fun getHotels(
        @Query("query") query: String
    ): List<Hotel>

    @GET("${Constant.API_HOTEL}/{hotelId}/${RATINGS}")
    suspend fun getListRating(
        @Path("hotelId") hotelId: Long
    ): List<Rating>

    @GET("${Constant.API_HOTEL}/{hotelId}/${AVGRATE}")
    suspend fun getAvgRating(
        @Path("hotelId") hotelId: Long
    ): Double
}