package com.example.booking_hotel.presentation.admin

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booking_hotel.data.remote.dto.HotelStat
import com.example.booking_hotel.domain.model.Hotel
import com.example.booking_hotel.domain.repository.HotelRepository
import com.example.booking_hotel.domain.repository.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminViewModel @Inject constructor(
    private val orderRepository: OrderRepository,
    private val hotelRepository: HotelRepository
):ViewModel() {
    private var _listTop10HotelStat=MutableLiveData<List<HotelStatDTO>>(emptyList())
    val listTop10HotelStat:LiveData<List<HotelStatDTO>> =_listTop10HotelStat
    private var _listHotel=MutableLiveData<List<Hotel>>(emptyList())
    val listHotel:LiveData<List<Hotel>> =_listHotel
    init {
        getAllHotel()
        getAllHotelStat()
    }
    private fun getAllHotelStat() {
        viewModelScope.launch {
            val stats = orderRepository.getAllHotelStat()
            val fullStats = stats.mapNotNull { stat ->
                val hotel = hotelRepository.getHotelById(stat.hotelId)
                hotel?.let {
                    HotelStatDTO(hotel = it, totalOrder = stat.totalOrder)
                }
            }
            _listTop10HotelStat.value = fullStats
        }
    }
    private fun getAllHotel(){
        viewModelScope.launch {
            _listHotel.value=hotelRepository.getAllHotel()
        }
    }
}