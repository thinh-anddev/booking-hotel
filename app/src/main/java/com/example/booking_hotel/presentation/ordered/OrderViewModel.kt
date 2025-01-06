package com.example.booking_hotel.presentation.ordered

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booking_hotel.domain.model.Hotel
import com.example.booking_hotel.domain.model.Order
import com.example.booking_hotel.domain.repository.HotelRepository
import com.example.booking_hotel.domain.repository.OrderRepository
import com.example.booking_hotel.helper.SharedPreferencesHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val orderRepository: OrderRepository,
    private val hotelRepository: HotelRepository,
    private val sharedPreferencesHelper: SharedPreferencesHelper
): ViewModel(){
    private var _listOrder = MutableLiveData<List<Order>>(emptyList())
    val listOrder: LiveData<List<Order>> = _listOrder
    init {
        getListOrder()
    }
    private fun getListOrder() {
        viewModelScope.launch {
            val listOrder = orderRepository.getListOrder(sharedPreferencesHelper.getUserId())
            _listOrder.value = listOrder?.orderList ?: emptyList()
        }
    }
    fun getOrderById(id: Long) {
        viewModelScope.launch {
            val order = orderRepository.getOrderById(id)
        }
    }
    fun updateOrderStatus(id: Long) {
        viewModelScope.launch {
            val response = orderRepository.updateOrderStatus(id, "")
        }
    }
    suspend fun getHotelById(id: Long): Hotel {
        return hotelRepository.getHotelById(id)
    }
}