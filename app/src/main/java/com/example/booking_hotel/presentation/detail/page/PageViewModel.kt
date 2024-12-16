package com.example.booking_hotel.presentation.detail.page

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booking_hotel.domain.model.Rating
import com.example.booking_hotel.domain.usecase.GetAvgRatingUsecase
import com.example.booking_hotel.domain.usecase.GetListRatingByHotelUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PageViewModel @Inject constructor(
    private val getListRatingUsecase: GetListRatingByHotelUsecase,
    private val getAvgRatingUsecase: GetAvgRatingUsecase
): ViewModel() {

    private var _ratings = MutableLiveData<List<Rating>>()
    val ratings: LiveData<List<Rating>> = _ratings

    private var _avgRating = MutableLiveData<Double>()
    val avgRating: LiveData<Double> = _avgRating

    fun getListRating(hotelId: Long) {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                val ratingList = getListRatingUsecase.invoke(hotelId)
                _ratings.value = ratingList
            }
        } catch (e: Exception) {
            Log.e("PageViewModel", "Error fetching ratings: ${e.message}")
        }
    }

    fun getAvgRating(hotelId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            _avgRating.postValue(getAvgRatingUsecase.invoke(hotelId))
        }
    }
}