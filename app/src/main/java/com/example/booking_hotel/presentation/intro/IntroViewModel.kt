package com.example.booking_hotel.presentation.intro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.booking_hotel.helper.SharedPreferencesHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IntroViewModel @Inject constructor(
    sharedPreferencesHelper: SharedPreferencesHelper
): ViewModel() {
    private var _isHaveUser = MutableLiveData(false)
    val isHaveUser: LiveData<Boolean> = _isHaveUser

    init {
        _isHaveUser.value = sharedPreferencesHelper.isLogin()
    }
}