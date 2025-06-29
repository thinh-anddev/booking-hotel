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
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.example.booking_hotel.data.remote.RecommendedHotelPagingSource
import com.example.booking_hotel.domain.model.Hotel
import com.example.booking_hotel.domain.repository.HotelRepository
import com.example.booking_hotel.domain.repository.UserRepository
import com.example.booking_hotel.domain.usecase.SearchHotelUsecase
import com.example.booking_hotel.helper.Constant
import com.example.booking_hotel.helper.SharedPreferencesHelper
import com.example.booking_hotel.helper.convertMillisToLocalDate
import com.example.booking_hotel.helper.dateToString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import kotlinx.coroutines.flow.flow

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val searchHotelUsecase: SearchHotelUsecase,
    private val userRepository: UserRepository,
    private val hotelRepository: HotelRepository,
    private val sharedPreferencesHelper: SharedPreferencesHelper
): ViewModel() {
    private val _recommendedHotels = MutableStateFlow<PagingData<Hotel>>(PagingData.empty())
    val recommendedHotels: StateFlow<PagingData<Hotel>> = _recommendedHotels
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
    private var _avatar = MutableLiveData("")
    val avatar: LiveData<String> = _avatar

    init {
        initUser()
    }
    private fun initUser() {
        if (sharedPreferencesHelper.isLogin()) {
            viewModelScope.launch(Dispatchers.IO) {
                val userId = sharedPreferencesHelper.getUserId()
                val getUserResponse = userRepository.getUserById(userId)
                Log.d("getUserResponse", getUserResponse.toString())
                if (getUserResponse!!.message == "User found") {
                    val user = getUserResponse!!.user
                    _avatar.postValue("${Constant.BASE_URL}${user!!.avatar}")
                    fetchRecommendedHotels(userId)
                } else {
                    _avatar.postValue("")
                }
            }
        } else {
            _avatar.value = ""
        }
    }

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
        val localDate = _selectedDateMillis.value?.let { convertMillisToLocalDate(it) }
        if (isCheckIn) {
            _checkInDate.value = localDate?.let { dateToString(it) }.toString()
        } else _checkOutDate.value = localDate?.let { dateToString(it) }.toString()
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
    fun fetchRecommendedHotels(userId: Long) {
        viewModelScope.launch {
            try {
                val ids = hotelRepository.getRecommendedHotels(userId)
                Log.d("RECOMMEND", "✅ Danh sách ID gợi ý: $ids")

                Pager(
                    config = PagingConfig(pageSize = 5),
                    pagingSourceFactory = {
                        RecommendedHotelPagingSource(
                            hotelRepository = hotelRepository,
                            recommendedIds = ids
                        )
                    }
                ).flow.cachedIn(viewModelScope).collect {
                    _recommendedHotels.value = it
                }

            } catch (e: Exception) {
                Log.e("RECOMMEND", "❌ Lỗi khi gọi gợi ý: ${e.message}")
            }
        }
    }
}