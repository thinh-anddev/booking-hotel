package com.example.booking_hotel.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.booking_hotel.data.remote.HotelAPI
import com.example.booking_hotel.data.remote.HotelPagingSource
import com.example.booking_hotel.data.remote.dto.HotelResponse
import com.example.booking_hotel.domain.model.Property
import com.example.booking_hotel.domain.repository.HotelRepository
import kotlinx.coroutines.flow.Flow

class HotelRepositoryImpl(
    private val hotelAPI: HotelAPI
): HotelRepository {
    override fun searchHotels(
        checkInDate: String,
        checkOutDate: String,
        adults: String,
        children: String,
        searchQuery: String
    ): Flow<PagingData<Property>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                HotelPagingSource(
                    hotelAPI = hotelAPI,
                    checkInDate = checkInDate,
                    checkOutDate = checkOutDate,
                    adults = adults,
                    children = children,
                    searchQuery = searchQuery
                )
            }
        ).flow
    }

}