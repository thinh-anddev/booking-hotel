package com.example.booking_hotel.domain.repository

import androidx.paging.PagingData
import com.example.booking_hotel.data.remote.dto.HotelDTO
import com.example.booking_hotel.data.remote.dto.HotelStat
import com.example.booking_hotel.domain.model.Hotel
import com.example.booking_hotel.domain.model.Rating
import kotlinx.coroutines.flow.Flow


interface HotelRepository {
    fun searchHotels(
        searchQuery: String,
    ) : Flow<PagingData<Hotel>>

    suspend fun getListRating(
        hotelId: Long
    ): List<Rating>

    suspend fun getAvgRate(
        hotelId: Long
    ): Double

    suspend fun getTotalRate(
        hotelId: Long
    ): Int

    suspend fun getCountStar(
        hotelId: Long
    ): Map<Int, Int>
    suspend fun getHotelById(
        hotelId: Long
    ): Hotel
    suspend fun getAllHotel():List<Hotel>
    suspend fun createHotel(hotel: HotelDTO): HotelDTO
    suspend fun deleteHotel(id:Long): String
    suspend fun getRecommendedHotels(userId: Long): List<Int>
    suspend fun refreshRecommendationModel(): Map<String, String>
}