package com.example.booking_hotel.domain.usecase

import androidx.paging.PagingData
import com.example.booking_hotel.data.remote.dto.HotelResponse
import com.example.booking_hotel.domain.model.Property
import com.example.booking_hotel.domain.repository.HotelRepository
import kotlinx.coroutines.flow.Flow

class SearchHotelUsecase(
    private val hotelRepository: HotelRepository
) {
    operator fun invoke(
        checkInDate: String,
        checkOutDate: String,
        adults: String,
        children: String,
        searchQuery: String
    ): Flow<PagingData<Property>> {
        return hotelRepository.searchHotels(
            checkInDate = checkInDate,
            checkOutDate = checkOutDate,
            adults = adults,
            children = children,
            searchQuery = searchQuery
        )
    }
}