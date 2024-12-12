package com.example.booking_hotel.domain.repository

import androidx.paging.PagingData
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
}