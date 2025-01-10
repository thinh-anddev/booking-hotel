package com.example.booking_hotel.presentation.confirm_order

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.booking_hotel.domain.model.Order
import com.example.booking_hotel.domain.repository.OrderRepository
import com.example.booking_hotel.helper.Constant
import com.example.booking_hotel.helper.SharedPreferencesHelper
import com.example.booking_hotel.presentation.navgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConfirmOrderViewModel @Inject constructor(
    private val orderRepository: OrderRepository,
    private val sharedPreferencesHelper: SharedPreferencesHelper
) : ViewModel() {
    fun saveOrder(
        hotelId: Long,
        orderCode: String,
        numberPeople: Int,
        totalPrice: Double,
        orderContact: String,
        orderEmail: String,
        orderName: String,
        navController: NavController
    ) {
        viewModelScope.launch {
            val order = Order(
                dateCreated = "",
                hotelId = hotelId,
                orderCode = orderCode,
                orderStatus = Constant.PENDING,
                userId = sharedPreferencesHelper.getUserId(),
                numberPeople = numberPeople,
                totalPrice = totalPrice,
                orderName = orderName,
                orderContact = orderContact,
                orderEmail = orderEmail
            )
            orderRepository.saveOrder(order)
            navController.navigate(Route.NavigatorScreen.route) {
                popUpTo(0) {
                    inclusive = true
                }
            }
            Log.d("saveOrder", order.toString())
        }
    }
}