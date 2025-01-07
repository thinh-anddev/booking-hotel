package com.example.booking_hotel.presentation.forgot_password

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.booking_hotel.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {
    private var _email = MutableLiveData("")
    val email: LiveData<String> = _email
    private var _error = MutableLiveData("")
    val error: LiveData<String> = _error

    fun onEmailChange(email: String){
        _email.value = email
    }
    fun forgotPassword(navController: NavController) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val email = _email.value
                if (email.isNullOrEmpty()) {
                    _error.postValue("Email cannot be empty")
                    return@launch
                }
                val response = userRepository.forgotPassword(email)
                if (response == "successfully") {
                    _error.postValue(response)
                    withContext(Dispatchers.Main) {
                        navController.popBackStack()
                    }
                } else {
                    _error.postValue(response)
                }
            } catch (e: Exception) {
                _error.postValue("An error occurred: ${e.message}")
            }
        }
    }

}