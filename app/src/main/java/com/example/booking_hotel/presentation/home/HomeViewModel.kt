package com.example.booking_hotel.presentation.home

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.example.booking_hotel.domain.model.Hotel
import com.example.booking_hotel.domain.usecase.SearchHotelUsecase
import com.example.booking_hotel.helper.DateUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val searchHotelUsecase: SearchHotelUsecase
): ViewModel() {

    private val _searchQuery = mutableStateOf("")
    var searchQuery: State<String> = _searchQuery

    private val _adult = mutableIntStateOf(1)
    var adult: State<Int> = _adult

    private val _children = mutableIntStateOf(1)
    var children: State<Int> = _children

    private val _checkInDate = mutableStateOf("")
    var checkInDate: State<String> = _checkInDate

    private val _checkOutDate = mutableStateOf("")
    var checkOutDate: State<String> = _checkOutDate

    private val _selectedDateMillis = MutableLiveData<Long>()
    val selectedDateMillis: LiveData<Long> get() = _selectedDateMillis

//    init {
//        getListHotSearch()
//    }

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

    //lay du lieu
    @RequiresApi(Build.VERSION_CODES.O)
    fun setSelectedDateMillis(dateMillis: Long, isCheckIn: Boolean) {
        _selectedDateMillis.value = dateMillis
        convertDateToString(isCheckIn)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun convertDateToString(isCheckIn: Boolean) {
        val localDate = _selectedDateMillis.value?.let { DateUtils().convertMillisToLocalDate(it) }
        if (isCheckIn) {
            _checkInDate.value = localDate?.let { DateUtils().dateToString(it) }.toString()
        } else _checkOutDate.value = localDate?.let { DateUtils().dateToString(it) }.toString()
    }

    fun onSearchChange(query: String) {
        _searchQuery.value = query
    }

    fun countAdult(isPlus: Boolean) {
        if (isPlus) {
            if (_adult.value <= 20) {
                _adult.value++
            }
        } else {
            if (_adult.value > 1) {
                _adult.value--
            }
        }
    }

    fun countChildren(isPlus: Boolean) {
        if (isPlus) {
            if (_children.value <= 20) {
                _children.value++
            }
         } else {
             if (_children.value > 1) {
                 _children.value--
             }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getListHotSearch(): Flow<PagingData<Hotel>> {
        var properties: Flow<PagingData<Hotel>>? = null
        try {
            properties = searchHotelUsecase.invoke(
                query = "quang ngai"
            ).cachedIn(viewModelScope)
            Log.d("API_TEST", "Response: $properties")
        } catch (e: Exception) {
            Log.e("API_TEST", "Error: ${e.message}")
        }
        return properties!!
    }
}