package com.example.booking_hotel.presentation.home

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
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

    private val _searchQuery = mutableStateOf("")
    var searchQuery: State<String> = _searchQuery

    private val _adult = mutableIntStateOf(1)
    var adult: State<Int> = _adult

    private val _children = mutableIntStateOf(1)
    var children: State<Int> = _children

    fun onSearchChange(query: String) {
        _searchQuery.value = query
    }

    fun countAdult(isPlus: Boolean) {
        if (isPlus) {
            if (_adult.value <= 20) {
                _adult.value++
            }
        } else {
            if (_adult.value > 1) {
                _adult.value--
            }
        }
    }

    fun countChildren(isPlus: Boolean) {
        if (isPlus) {
            if (_children.value <= 20) {
                _children.value++
            }
         } else {
             if (_children.value > 1) {
                 _children.value--
             }
        }
    }

    fun search() {
        try {
            val properties = searchHotelUsecase.invoke(
                checkOutDate = "2024-07-31",
                checkInDate =  "2024-07-30",
                adults = "1",
                children = "1",
                searchQuery = _searchQuery.value
            ).cachedIn(viewModelScope)
            _searchQuery.value = ""
            Log.d("API_TEST", "Response: $properties")
        } catch (e: Exception) {
            Log.e("API_TEST", "Error: ${e.message}")
        }
    }
}