package com.example.booking_hotel.presentation.detail.page

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booking_hotel.domain.repository.HotelRepository
import com.example.booking_hotel.domain.usecase.GetAvgRatingUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class PageViewModel @Inject constructor(
    private val getAvgRatingUsecase: GetAvgRatingUsecase,
    private val hotelRepository: HotelRepository
): ViewModel() {

    private var _avgRating = MutableLiveData<Double>()
    val avgRating: LiveData<Double> = _avgRating

    private var _totalRate = MutableLiveData<Int>()
    val totalRate: LiveData<Int> = _totalRate

    private var _countStar = MutableLiveData<Map<Int, Int>>()
    val countStar: LiveData<Map<Int, Int>> = _countStar

    private fun roundToTwoDecimalPlace(number: Double): Double {
        return (number * 100).roundToInt() / 100.0
    }

    fun getAvgRating(hotelId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val avgRating = roundToTwoDecimalPlace(getAvgRatingUsecase.invoke(hotelId))
            _avgRating.postValue(avgRating)
        }
    }

    fun getTotalRate(hotelId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val totalRate = hotelRepository.getTotalRate(hotelId)
            _totalRate.postValue(totalRate)
        }
    }

    fun getCountStar(hotelId: Long) {
        viewModelScope.launch(
            Dispatchers.IO
        ) {
            val map = hotelRepository.getCountStar(hotelId)
            _countStar.postValue(map)
        }
    }
}