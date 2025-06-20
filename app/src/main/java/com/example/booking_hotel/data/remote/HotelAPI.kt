package com.example.booking_hotel.data.remote

import com.example.booking_hotel.data.remote.dto.HotelDTO
import com.example.booking_hotel.data.remote.dto.HotelResponse
import com.example.booking_hotel.domain.model.Hotel
import com.example.booking_hotel.domain.model.Rating
import com.example.booking_hotel.helper.Constant
import com.example.booking_hotel.helper.Constant.AVGRATE
import com.example.booking_hotel.helper.Constant.RATINGS
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
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

    @GET("${Constant.API_HOTEL}/${Constant.GET_ALL_HOTEL}")
    suspend fun getALlHotel():List<Hotel>

    @GET("${Constant.API_HOTEL}/{hotelId}/${AVGRATE}")
    suspend fun getAvgRating(
        @Path("hotelId") hotelId: Long
    ): Double

    @GET("${Constant.API_HOTEL}/{hotelId}/${Constant.TOTAL_RATE}")
    suspend fun getTotalRate(
        @Path("hotelId") hotelId: Long
    ): Int

    @GET("${Constant.API_HOTEL}/{hotelId}/${Constant.COUNT_STAR}")
    suspend fun getCountStar(
        @Path("hotelId") hotelId: Long
    ): Map<Int, Int>

    @GET("${Constant.API_HOTEL}/${Constant.GET_HOTEL}/{id}")
    suspend fun getHotelById(
        @Path("id") id: Long
    ): Response<Hotel>
    @POST("${Constant.API_HOTEL}/add")
    suspend fun createHotel(@Body hotel: HotelDTO): Response<HotelDTO>
    @DELETE("${Constant.API_HOTEL}/{id}")
    suspend fun deleteHotel(
        @Path("id") hotelId: Long
    ): Response<String>
    @GET("/api/recommend")
    suspend fun getRecommendedHotels(
        @Query("userId") userId: Long
    ): List<Int>
    @POST("/api/refresh-model")
    suspend fun refreshRecommendationModel(): Map<String, String>
}