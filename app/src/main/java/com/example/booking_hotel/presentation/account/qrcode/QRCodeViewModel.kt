package com.example.booking_hotel.presentation.account.qrcode

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booking_hotel.domain.repository.UserRepository
import com.example.booking_hotel.helper.SharedPreferencesHelper
import com.example.booking_hotel.helper.generateQRCodeBitmap
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QRCodeViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val sharedPreferencesHelper: SharedPreferencesHelper
): ViewModel() {
    private var _bitmap = MutableLiveData<Bitmap>()
    val bitmap: LiveData<Bitmap> = _bitmap
    init {
        saveUserToQR()
    }
    private fun saveUserToQR() {
        viewModelScope.launch {
            val response = userRepository.getUserById(sharedPreferencesHelper.getUserId())
            val user = response?.user
            val information = "${user?.userName} - ${user?.password}"
            _bitmap.value = generateQRCodeBitmap(information)
        }
    }
}