package com.example.booking_hotel.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.booking_hotel.data.remote.HotelAPI
import com.example.booking_hotel.data.remote.HotelPagingSource
import com.example.booking_hotel.domain.model.Hotel
import com.example.booking_hotel.domain.model.Rating
import com.example.booking_hotel.domain.repository.HotelRepository
import kotlinx.coroutines.flow.Flow

class HotelRepositoryImpl(
    private val hotelAPI: HotelAPI
): HotelRepository {

    override fun searchHotels(query: String): Flow<PagingData<Hotel>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                HotelPagingSource(
                    hotelAPI = hotelAPI,
                    searchQuery = query
                )
            }
        ).flow
    }

    override suspend fun getListRating(hotelId: Long): List<Rating> {
        return hotelAPI.getListRating(hotelId = hotelId)
    }

    override suspend fun getAvgRate(hotelId: Long): Double {
        return hotelAPI.getAvgRating(hotelId = hotelId)
    }

    override suspend fun getTotalRate(hotelId: Long): Int {
        return hotelAPI.getTotalRate(hotelId = hotelId)
    }

    override suspend fun getCountStar(hotelId: Long): Map<Int, Int> {
        return hotelAPI.getCountStar(hotelId)
    }

    override suspend fun getHotelById(hotelId: Long): Hotel {
        val response = hotelAPI.getHotelById(hotelId)
        return response.body()!!
    }
}
