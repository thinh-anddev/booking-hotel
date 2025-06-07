package com.example.booking_hotel.presentation.admin

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booking_hotel.data.remote.dto.HotelDTO
import com.example.booking_hotel.data.remote.dto.HotelStat
import com.example.booking_hotel.data.remote.dto.RevenueResponse
import com.example.booking_hotel.domain.model.Hotel
import com.example.booking_hotel.domain.repository.HotelRepository
import com.example.booking_hotel.domain.repository.ImageRepository
import com.example.booking_hotel.domain.repository.OrderRepository
import com.example.booking_hotel.helper.prepareFilePart
import com.example.booking_hotel.helper.uriToFile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject
import androidx.core.net.toUri

@HiltViewModel
class AdminViewModel @Inject constructor(
    private val orderRepository: OrderRepository,
    private val hotelRepository: HotelRepository,
    private val imageRepository:ImageRepository
):ViewModel() {
    private var _listTop10HotelStat=MutableLiveData<List<HotelStatDTO>>(emptyList())
    val listTop10HotelStat:LiveData<List<HotelStatDTO>> =_listTop10HotelStat
    private var _listHotel=MutableLiveData<List<Hotel>>(emptyList())
    val listHotel:LiveData<List<Hotel>> =_listHotel
    private var _mostBookedHotel= MutableLiveData<HotelStatDTO>()
    val mostBookedHotel:LiveData<HotelStatDTO> = _mostBookedHotel
    private val _selectedRevenue = MutableLiveData<RevenueResponse?>()
    val selectedRevenue: LiveData<RevenueResponse?> = _selectedRevenue
    init {
        getAllHotel()
        getAllHotelStat()
        //getMostBookHotel()
    }
    private fun getAllHotelStat() {
        viewModelScope.launch {
            val stats = orderRepository.getTop10HotelStat()
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
    private fun getMostBookHotel(){
        viewModelScope.launch {
            val mostBook=orderRepository.getMostBookHotel()
            val hotel = hotelRepository.getHotelById(mostBook.hotelId)
            hotel.let {
                _mostBookedHotel.value= HotelStatDTO(hotel = it, totalOrder = mostBook.totalOrder)
            }
        }
    }
    fun fetchRevenue(hotelId: Long, month: Int, year: Int) {
        viewModelScope.launch {
            try {
                val response = orderRepository.getMonthlyRevenue(year, hotelId, month)
                _selectedRevenue.value=response
            } catch (e: Exception) {
                _selectedRevenue.value = null
            }
        }
    }
    fun uploadImage(file: File) {
        viewModelScope.launch {
            try {
                val response = imageRepository.uploadImage(file)
                Log.d("AvatarURL", response.url)
            } catch (e: Exception) {
                Log.e("UploadError", e.message ?: "Unknown error")
            }
        }
    }
    fun addHotel(hotelDto: HotelDTO, context: Context) {
        viewModelScope.launch {
            hotelRepository.createHotel(hotelDto)
            if (hotelDto.images?.isNotEmpty() == true) {
                val file = uriToFile(hotelDto.images!![0].originalImage.toUri(), context)
                imageRepository.uploadImage(file)
            }
        }
    }
}