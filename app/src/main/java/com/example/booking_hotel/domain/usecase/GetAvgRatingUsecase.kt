package com.example.booking_hotel.domain.usecase

import com.example.booking_hotel.domain.repository.HotelRepository
import javax.inject.Inject

class GetAvgRatingUsecase @Inject constructor(
    private val hotelRepository: HotelRepository
){
    suspend operator fun invoke(hotelId: Long): Double {
        return hotelRepository.getAvgRate(hotelId)
    }
}