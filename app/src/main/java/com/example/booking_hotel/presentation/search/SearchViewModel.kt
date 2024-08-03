package com.example.booking_hotel.presentation.search

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.booking_hotel.domain.model.Property
import com.example.booking_hotel.domain.usecase.SearchHotelUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchHotelUsecase: SearchHotelUsecase
): ViewModel() {

    fun searchByQuery(
        query: String,
        checkInDate: String,
        checkOutDate: String,
        adult: String,
        children: String
    ): Flow<PagingData<Property>> {
        val hotels = searchHotelUsecase(
            searchQuery = query,
            checkInDate = checkInDate,
            checkOutDate = checkOutDate,
            adults = adult,
            children = children
        ).cachedIn(viewModelScope)
        return hotels
    }
}