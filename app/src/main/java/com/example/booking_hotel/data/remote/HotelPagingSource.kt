package com.example.booking_hotel.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.booking_hotel.domain.model.Property

class HotelPagingSource(
    private val hotelAPI: HotelAPI,
    private val checkInDate: String,
    private val checkOutDate: String,
    private val adults: String,
    private val children: String,
    private val searchQuery: String,
    private val sortBy: String // hottest hotel = 13
): PagingSource<Int, Property>() {

    private var totalHotelCount = 0
    override fun getRefreshKey(state: PagingState<Int, Property>): Int? {
        return state.anchorPosition?.let {
            anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Property> {
        val page = params.key ?: 1
        return try {
            val hotelResponse = hotelAPI.getHotels(
                checkInDate =  checkInDate,
                checkOutDate = checkOutDate,
                adults = adults,
                children = children,
                searchQuery = searchQuery,
                sortBy = sortBy,
                page = page
            )
            totalHotelCount += hotelResponse.properties.size
            val properties = hotelResponse.properties.distinctBy { it.name } //remove duplicate

            LoadResult.Page(
                data = properties,
                nextKey = if (totalHotelCount == properties.size) null else page + 1,
                prevKey = null
            )
        } catch (e: Exception) {
            LoadResult.Error(
                throwable = e
            )
        }
    }

}