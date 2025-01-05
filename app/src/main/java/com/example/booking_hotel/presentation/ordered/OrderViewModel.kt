package com.example.booking_hotel.presentation.ordered

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booking_hotel.domain.repository.OrderRepository
import com.example.booking_hotel.helper.SharedPreferencesHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val orderRepository: OrderRepository,
    private val sharedPreferencesHelper: SharedPreferencesHelper
): ViewModel(){

    fun getOrderById(id: Long) {
        viewModelScope.launch {
            val order = orderRepository.getOrderById(id)
        }
    }
    fun getListOrder() {
        viewModelScope.launch {
            val listOrder = orderRepository.getListOrder(sharedPreferencesHelper.getUserId())
        }
    }
    fun updateOrderStatus(id: Long) {
        viewModelScope.launch {
            val response = orderRepository.updateOrderStatus(id, "")
        }
    }
}