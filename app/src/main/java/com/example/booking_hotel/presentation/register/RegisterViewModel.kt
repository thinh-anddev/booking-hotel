package com.example.booking_hotel.presentation.register

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel(){

    private val _email = mutableStateOf("")
    val email: State<String> = _email

    private val _contact = mutableStateOf("")
    val contact: State<String> = _contact

    private val _password = mutableStateOf("")
    val password: State<String> = _password

    private val _confirmPassword = mutableStateOf("")
    val confirmPassword: State<String> = _confirmPassword

    private val _errorMessage = mutableStateOf("")
    val errorMessage: State<String> = _errorMessage

    fun onEmailChange(email: String) {
        _email.value = email
    }

    fun onContactChange(contact: String) {
        _contact.value = contact
    }

    fun onPasswordChange(password: String) {
        _password.value = password
    }

    fun onConfirmPasswordChange(confirmnPassword: String) {
        _confirmPassword.value = confirmnPassword
    }

    fun register() {
        viewModelScope.launch {
            if (_email.value.isBlank() || _contact.value.isBlank() || _password.value.isBlank() || _confirmPassword.value.isBlank()) {
                _errorMessage.value = "Vui lòng nhập đầy đủ thông tin"
            } else if (_password.value != _confirmPassword.value) {
                _errorMessage.value = "Mật khẩu không trùng khớp"
            } else if (_password.value.length <= 8 ) {
                _errorMessage.value = "Hãy nhập mật khẩu dài hơn"
            }
        }
    }
}