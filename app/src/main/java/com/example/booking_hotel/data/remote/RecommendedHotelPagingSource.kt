package com.example.booking_hotel.data.remote

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.booking_hotel.domain.model.Hotel
import com.example.booking_hotel.domain.repository.HotelRepository

class RecommendedHotelPagingSource(
    private val hotelRepository: HotelRepository,
    private val recommendedIds: List<Int>
) : PagingSource<Int, Hotel>() {

    override fun getRefreshKey(state: PagingState<Int, Hotel>): Int? {
        return state.anchorPosition?.let { position ->
            val anchorPage = state.closestPageToPosition(position)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Hotel> {
        val page = params.key ?: 0
        val pageSize = params.loadSize

        return try {
            val start = page * pageSize
            val end = minOf(start + pageSize, recommendedIds.size)

            val currentIds = recommendedIds.subList(start, end)

            val hotels = currentIds.mapNotNull { id ->
                try {
                    val hotel = hotelRepository.getHotelById(id.toLong())
                    Log.d("RecommendedPaging", "✔️ Loaded hotel id=$id: ${hotel.name}")
                    hotel
                } catch (e: Exception) {
                    Log.w("RecommendedPaging", "⚠️ Failed to load hotel id=$id: ${e.message}")
                    null
                }
            }

            LoadResult.Page(
                data = hotels,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (end >= recommendedIds.size) null else page + 1
            )
        } catch (e: Exception) {
            Log.e("RecommendedPaging", "❌ Load error: ${e.message}")
            LoadResult.Error(e)
        }
    }
}
