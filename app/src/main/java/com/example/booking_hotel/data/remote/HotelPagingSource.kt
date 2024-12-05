package com.example.booking_hotel.data.remote

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.booking_hotel.domain.model.Hotel

class HotelPagingSource(
    private val hotelAPI: HotelAPI,
    private val searchQuery: String
) : PagingSource<Int, Hotel>() {

    private var totalHotelCount = 0

    override fun getRefreshKey(state: PagingState<Int, Hotel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Hotel> {
        val page = params.key ?: 1
        Log.d("HotelPagingSource", "Loading page $page")
        return try {
            val hotelResponse = hotelAPI.getHotels(query = searchQuery)
            Log.d("HotelPagingSource", "hotelResponse: ${hotelResponse.size}")
            totalHotelCount += hotelResponse.size
            val properties = hotelResponse.distinctBy { it.name }

            LoadResult.Page(
                data = properties,
                nextKey = if (totalHotelCount == properties.size) null else page + 1,
                prevKey = null
            )
        } catch (e: Exception) {
            LoadResult.Error(throwable = e)
        }
    }
}
