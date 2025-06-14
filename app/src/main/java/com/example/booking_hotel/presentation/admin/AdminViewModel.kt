package com.example.booking_hotel.presentation.admin

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
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
import androidx.navigation.NavController
import com.example.booking_hotel.data.remote.dto.UpdateUserForAdminRequest
import com.example.booking_hotel.data.remote.dto.UpdateUserRequest
import com.example.booking_hotel.domain.model.User
import com.example.booking_hotel.domain.repository.UserRepository
import com.example.booking_hotel.presentation.navgraph.Route

@HiltViewModel
class AdminViewModel @Inject constructor(
    private val orderRepository: OrderRepository,
    private val hotelRepository: HotelRepository,
    private val imageRepository:ImageRepository,
    private val userRepository: UserRepository
):ViewModel() {
    private var _listTop10HotelStat=MutableLiveData<List<HotelStatDTO>>(emptyList())
    val listTop10HotelStat:LiveData<List<HotelStatDTO>> =_listTop10HotelStat
    private var _listHotel=MutableLiveData<List<Hotel>>(emptyList())
    val listHotel:LiveData<List<Hotel>> =_listHotel
    private var _mostBookedHotel= MutableLiveData<HotelStatDTO>()
    val mostBookedHotel:LiveData<HotelStatDTO> = _mostBookedHotel
    private val _selectedRevenue = MutableLiveData<RevenueResponse?>()
    val selectedRevenue: LiveData<RevenueResponse?> = _selectedRevenue
    private var _listUser=MutableLiveData<List<User>>(emptyList())
    val listUser:LiveData<List<User>> = _listUser

    private var _avatar = MutableLiveData("")
    val avatar: LiveData<String> = _avatar
    init {
        getAllHotel()
        getAllHotelStat()
        //getMostBookHotel()
        findAllUser()
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
    fun addHotel(hotelDto: HotelDTO, context: Context) {
        viewModelScope.launch {
            hotelRepository.createHotel(hotelDto)
            if (hotelDto.images?.isNotEmpty() == true) {
                val file = uriToFile(hotelDto.images!![0].originalImage.toUri(), context)
                imageRepository.uploadImage(file)
            }
        }
    }
    fun findAllUser(){
        viewModelScope.launch {
            _listUser.value=userRepository.findAll()
        }
    }
    fun onSelectedAvatar(url: String){
        _avatar.value=url
    }
    fun updateUser(navController: NavController,context:Context,user: User) {
        viewModelScope.launch {
            val file = uriToFile(Uri.parse(_avatar.value) , context)
            val result = imageRepository.uploadImage(file)
            _avatar.value = result.url
            val updateUserRequest = UpdateUserForAdminRequest(
                contact = user.contact,
                email = user.email,
                avatar = _avatar.value!!,
                userName = user.email,
                password = user.password,
                age=user.age
            )
            userRepository.updateUserForAdmin(user.id!!, updateUserRequest)
            navController.navigate(Route.AdminScreen.route) {
                popUpTo(0) { inclusive = false}
            }
        }
    }
    fun deleteUser(navController: NavController, context: Context, user: User) {
        viewModelScope.launch {
            try {
                val response = userRepository.deleteUser(user.id!!)
                if (response=="User deleted successfully") {
                    Toast.makeText(context, "Đã xóa người dùng", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                } else {
                    Toast.makeText(context,"Lỗi xóa", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(context, "Lỗi: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}