package com.example.booking_hotel.presentation.ordered

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booking_hotel.domain.model.Hotel
import com.example.booking_hotel.domain.model.Order
import com.example.booking_hotel.domain.repository.HotelRepository
import com.example.booking_hotel.domain.repository.OrderRepository
import com.example.booking_hotel.helper.Constant
import com.example.booking_hotel.helper.SharedPreferencesHelper
import com.example.booking_hotel.ui.theme.Color_986601
import com.example.booking_hotel.ui.theme.ContentColor
import com.example.booking_hotel.ui.theme.TextColor
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
    private var _nameFunction = MutableLiveData("")
    val nameFunction: LiveData<String> = _nameFunction
    private var _backgroundFunction = MutableLiveData(TextColor)
    val backgroundColor: LiveData<Color> = _backgroundFunction
    init {
        getListOrder()
    }
    private fun getListOrder() {
        viewModelScope.launch {
            val listOrder = orderRepository.getListOrder(sharedPreferencesHelper.getUserId())
            _listOrder.value = listOrder?.orderList ?: emptyList()
        }
    }
    fun syncDataStatus(status: String) {
        val name = when (status) {
            Constant.PENDING -> "Thanh toán"
            Constant.CANCELED -> "Hủy phòng"
            Constant.CHECK_OUT -> "Đánh giá"
            Constant.PAID -> "Hủy phòng"
            else -> ""
        }
        _nameFunction.value = name
//        val backgroundFunction = when (order.orderStatus) {
//            Constant.PENDING -> TextColor
//            Constant.CANCELED -> ContentColor
//            Constant.CHECK_OUT -> Color_986601
//            Constant.PAID -> Color_986601
//            else -> TextColor
//        }
    }
    fun syncDataBackground(status: String) {
        val backgroundFunction = when (status) {
            Constant.PENDING -> TextColor
            Constant.CANCELED -> ContentColor
            Constant.CHECK_OUT -> Color_986601
            Constant.PAID -> Color_986601
            else -> TextColor
        }
        _backgroundFunction.value = backgroundFunction
    }
    fun getOrderById(id: Long) {
        viewModelScope.launch {
            val order = orderRepository.getOrderById(id)
        }
    }
    fun updateOrderStatus(id: Long, status: String) {
        Log.d("zzzzz","$id ---- $status")
        viewModelScope.launch {
            val response = orderRepository.updateOrderStatus(id, status)
        }
    }
    suspend fun getHotelById(id: Long): Hotel {
        return hotelRepository.getHotelById(id)
    }
}