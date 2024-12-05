package com.example.booking_hotel.presentation.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.cachedIn
import com.example.booking_hotel.domain.model.Hotel
import com.example.booking_hotel.domain.usecase.SearchHotelUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchHotelUsecase: SearchHotelUsecase
) : ViewModel() {

    private val _properties = MutableStateFlow<PagingData<Hotel>>(PagingData.empty())
    val properties: StateFlow<PagingData<Hotel>> = _properties

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.Price -> {
                sortByPrice()
            }

            is SearchEvent.View -> {
                sortByView()
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    private suspend fun <T : Any> PagingData<T>.toList(): List<T> {
        val flow = PagingData::class.java.getDeclaredField("flow").apply {
            isAccessible = true
        }.get(this) as Flow<Any?>
        val pageEventInsert = flow.single()
        val pageEventInsertClass = Class.forName("androidx.paging.PageEvent\$Insert")
        val pagesField = pageEventInsertClass.getDeclaredField("pages").apply {
            isAccessible = true
        }
        val pages = pagesField.get(pageEventInsert) as List<Any?>
        val transformablePageDataField =
            Class.forName("androidx.paging.TransformablePage").getDeclaredField("data").apply {
                isAccessible = true
            }
        val listItems =
            pages.flatMap { transformablePageDataField.get(it) as List<*> }
        return listItems as List<T>
    }

    suspend fun <T : Any> List<T>.toPagingData(): PagingData<T> {
        return Pager(PagingConfig(pageSize = this.size)) {
            object : PagingSource<Int, T>() {
                override fun getRefreshKey(state: PagingState<Int, T>): Int? = null
                override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
                    return LoadResult.Page(
                        data = this@toPagingData,
                        prevKey = null,
                        nextKey = null
                    )
                }
            }
        }.flow.cachedIn(viewModelScope).first()
    }

    private fun sortByPrice() {
        viewModelScope.launch {
            _properties.value?.let { pagingData ->
                try {
                    // Log dữ liệu ban đầu
                    Log.d("sortByPrice", "Initial data: ${pagingData.toList()}")

                    val sortedList = pagingData.toList().sortedBy { it.ratePerNight!!.lowest }
                    Log.d("sortByPrice", "Sorted data: $sortedList")

                    // Gọi hàm toPagingData và log kết quả
                    val pagingResult = sortedList.toPagingData()
                        Log.d("sortByPrice", "Paging data result: $pagingResult")

                    _properties.value = pagingResult
                    Log.d("sortByPrice", "Sorting completed successfully with ${sortedList.size} items")
                } catch (e: Exception) {
                    Log.e("sortByPrice", "Error during sorting: ${e.message}")
                }
            } ?: run {
                Log.e("sortByPrice", "_properties.value is null")
            }
        }
    }

    fun sortByView() {

    }

    fun searchByQuery(
        query: String,
        checkInDate: String,
        checkOutDate: String,
        adult: String,
        children: String
    ) {
        searchHotelUsecase(
//            checkInDate = checkInDate,
//            checkOutDate = checkOutDate,
//            adults = adult,
//            children = children,
            query = query,
        ).cachedIn(viewModelScope).also { flow ->
            viewModelScope.launch {
                flow.collectLatest { pagingData ->
                    _properties.value = pagingData
                }
            }
        }
    }
}

sealed class SearchEvent {
    object Price : SearchEvent()
    object View : SearchEvent()
}