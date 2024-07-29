package com.example.booking_hotel.domain.repository

import androidx.paging.PagingData
import com.example.booking_hotel.data.remote.HotelPagingSource
import com.example.booking_hotel.data.remote.dto.HotelResponse
import com.example.booking_hotel.domain.model.Property
import kotlinx.coroutines.flow.Flow


interface HotelRepository {
    fun searchHotels(
        checkInDate: String,
        checkOutDate: String,
        adults: String,
        children: String,
        searchQuery: String
    ) : Flow<PagingData<Property>>
}