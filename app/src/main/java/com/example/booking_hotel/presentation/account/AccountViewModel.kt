package com.example.booking_hotel.presentation.account

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booking_hotel.data.remote.dto.GetUserResponse
import com.example.booking_hotel.domain.model.User
import com.example.booking_hotel.domain.repository.UserRepository
import com.example.booking_hotel.helper.SharedPreferencesHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URI
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val sharedPreferencesHelper: SharedPreferencesHelper,
    private val userRepository: UserRepository
): ViewModel() {
    private var _name = MutableLiveData("")
    val name: LiveData<String> = _name

    private var _contact = MutableLiveData("")
    val contact: LiveData<String> = _contact

    private var _avatar = MutableLiveData("")
    val avatar: LiveData<String> = _avatar

    init {
        initUser()
    }
    private fun initUser() {
        viewModelScope.launch {
            if (sharedPreferencesHelper.isLogin()) {
                val user = getUserById()
                _avatar.value = user!!.avatar
                _name.value = user.userName
                _contact.value = user.contact
            } else {
                _avatar.value = ""
                _name.value = ""
                _contact.value = ""
            }
        }
    }

    private suspend fun getUserById(): User? {
        return withContext(Dispatchers.IO) {
            val getUserResponse = userRepository.getUserById(sharedPreferencesHelper.getUserId())
            if (getUserResponse?.message == "User found") {
                getUserResponse.user
            } else {
                null
            }
        }
    }
    fun onImageUriSelected(uri: Uri) {
        _avatar.value = uri.toString()
    }
}