package com.example.booking_hotel.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.booking_hotel.domain.model.Property
import com.example.booking_hotel.domain.usecase.SearchHotelUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val searchHotelUsecase: SearchHotelUsecase
): ViewModel() {

    private val _searchResult = MutableStateFlow<Flow<PagingData<Property>>>(flowOf(PagingData.empty()))
    val searchResult: StateFlow<Flow<PagingData<Property>>> = _searchResult.asStateFlow()

    fun search() {
        try {
            val properties = searchHotelUsecase.invoke(
                checkOutDate = "2024-07-31",
                checkInDate =  "2024-07-30",
                adults = "1",
                children = "1",
                searchQuery = "quang ngai"
            ).cachedIn(viewModelScope)
            _searchResult.value = properties
            Log.d("API_TEST", "Response: $properties")
        } catch (e: Exception) {
            Log.e("API_TEST", "Error: ${e.message}")
        }
    }
}