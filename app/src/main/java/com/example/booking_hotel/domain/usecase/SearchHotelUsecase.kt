package com.example.booking_hotel.domain.usecase

import androidx.paging.PagingData
import com.example.booking_hotel.domain.model.Hotel
import com.example.booking_hotel.domain.repository.HotelRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchHotelUsecase @Inject constructor(
    private val hotelRepository: HotelRepository
) {
    operator fun invoke(query: String): Flow<PagingData<Hotel>> {
        return hotelRepository.searchHotels(query)
    }
}