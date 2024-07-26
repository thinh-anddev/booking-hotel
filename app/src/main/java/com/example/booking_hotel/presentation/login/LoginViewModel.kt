package com.example.booking_hotel.presentation.login

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booking_hotel.test.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(

) : ViewModel(){

    private val _email = mutableStateOf("")
    val email: State<String> = _email

    private val _password = mutableStateOf("")
    var password: State<String> = _password

    private val _errorMessage = mutableStateOf("")
    var errorMessage: State<String> = _errorMessage

    private val _loginSuccess = mutableStateOf(false)
    val loginSuccess: MutableState<Boolean> get() = _loginSuccess

    fun onEmailChange(email: String) {
        _email.value = email
    }

    fun onPasswordChange(contact: String) {
        _password.value = contact
    }

    fun login() {
        viewModelScope.launch {
            if (_email.value.isBlank() || _password.value.isBlank()) {
                _errorMessage.value = "Vui lòng nhập đầy đủ thông tin"
            } else if (_email.value != User.USERNAME && _password.value != User.PASSWORD) {
                _errorMessage.value = "Tên đăng nhập hoặc mật khẩu không chính xác"
            } else if (_email.value == User.USERNAME && _password.value == User.PASSWORD) {
                _loginSuccess.value = true
            }
        }
    }
}