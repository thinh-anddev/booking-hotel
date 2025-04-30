package com.example.booking_hotel.presentation.admin

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booking_hotel.data.remote.dto.HotelStat
import com.example.booking_hotel.domain.repository.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminViewModel @Inject constructor(
    private val orderRepository: OrderRepository
):ViewModel() {
    private var _listTop10HotelStat=MutableLiveData<List<HotelStat>>(emptyList())
    val listTop10HotelStat:LiveData<List<HotelStat>> =_listTop10HotelStat
    init {
        getAllHotelStat()
    }
    private fun getAllHotelStat(){
        viewModelScope.launch {
            _listTop10HotelStat.value=orderRepository.getAllHotelStat()
        }
    }
}