package com.example.booking_hotel.domain.repository

import androidx.paging.PagingData
import com.example.booking_hotel.domain.model.Hotel
import kotlinx.coroutines.flow.Flow


interface HotelRepository {
    fun searchHotels(
        searchQuery: String,
    ) : Flow<PagingData<Hotel>>
}