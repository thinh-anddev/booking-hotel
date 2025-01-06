package com.example.booking_hotel.presentation.explore

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.booking_hotel.domain.model.Hotel
import com.example.booking_hotel.domain.model.Order
import com.example.booking_hotel.domain.repository.OrderRepository
import com.example.booking_hotel.domain.usecase.SearchHotelUsecase
import com.example.booking_hotel.helper.SharedPreferencesHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@SuppressLint("NewApi")
@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val usecase: SearchHotelUsecase,
    private val orderRepository: OrderRepository,
    private val sharedPreferencesHelper: SharedPreferencesHelper
): ViewModel() {

    private val _checkInDate = mutableStateOf("")
    private val _checkOutDate = mutableStateOf("")

    private val _hotels = MutableStateFlow<PagingData<Hotel>>(PagingData.empty())
    var hotels: StateFlow<PagingData<Hotel>> = _hotels
    private var _listOrder = MutableLiveData<List<Order>>(emptyList())
    val listOrder: LiveData<List<Order>> = _listOrder
    private fun getListOrder() {
        viewModelScope.launch {
            val listOrder = orderRepository.getListOrder(sharedPreferencesHelper.getUserId())
            _listOrder.value = listOrder?.orderList ?: emptyList()
        }
    }

    init {
        getListOrder()
        getListHotelExplore()
    }

    private fun getListHotelExplore() {
        viewModelScope.launch {
            usecase.invoke(
                query = "quang ngai",
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