package com.example.booking_hotel.domain.usecase

import com.example.booking_hotel.domain.model.Rating
import com.example.booking_hotel.domain.repository.HotelRepository
import javax.inject.Inject

class GetListRatingByHotelUsecase @Inject constructor(
    private val hotelRepository: HotelRepository
) {
    suspend operator fun invoke(hotelId: Long): List<Rating> {
        return hotelRepository.getListRating(hotelId)
    }
}