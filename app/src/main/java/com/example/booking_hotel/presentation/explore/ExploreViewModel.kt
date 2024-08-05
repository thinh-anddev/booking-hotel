package com.example.booking_hotel.presentation.explore

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.booking_hotel.domain.model.Property
import com.example.booking_hotel.domain.usecase.SearchHotelUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@SuppressLint("NewApi")
@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val usecase: SearchHotelUsecase
): ViewModel() {

    private val _checkInDate = mutableStateOf("")
    private val _checkOutDate = mutableStateOf("")

    private val _hotels = MutableStateFlow<PagingData<Property>>(PagingData.empty())
    var hotels: StateFlow<PagingData<Property>> = _hotels

    init {
        setCurrentDate()
        getListHotelExplore()
    }

    private fun getListHotelExplore() {
        viewModelScope.launch {
            usecase.invoke(
                checkOutDate = _checkOutDate.value,
                checkInDate =  _checkInDate.value,
                adults = "1",
                children = "1",
                searchQuery = "viet nam",
                sortBy = "" //hottest
            ).cachedIn(viewModelScope).collect {
                    pagingData ->
                _hotels.value = pagingData
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setCurrentDate() {
        if (_checkInDate.value == "" && _checkOutDate.value == "") {
            val currentDate = LocalDate.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val formattedDate = currentDate.format(formatter)
            _checkInDate.value = formattedDate
            _checkOutDate.value = formattedDate
        }
    }

}