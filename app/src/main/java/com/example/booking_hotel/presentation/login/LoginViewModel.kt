package com.example.booking_hotel.presentation.login

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.booking_hotel.domain.repository.UserRepository
import com.example.booking_hotel.presentation.navgraph.Route
import com.example.booking_hotel.test.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel(){

    private val _username = mutableStateOf("")
    val username: State<String> = _username

    private val _password = mutableStateOf("")
    var password: State<String> = _password

    private val _errorMessage = mutableStateOf("")
    var errorMessage: State<String> = _errorMessage

    private val _loginSuccess = mutableStateOf(false)
    val loginSuccess: MutableState<Boolean> get() = _loginSuccess

    fun onUsernameChange(username: String) {
        _username.value = username
    }

    fun onPasswordChange(contact: String) {
        _password.value = contact
    }

    fun login(navController: NavController) {
        viewModelScope.launch {
            val username = _username.value
            val password = _password.value
            if (username.isBlank() || password.isBlank()) {
                _errorMessage.value = "Vui lòng nhập đầy đủ thông tin"
            }
            val result = userRepository.login(username, password)
            if (result == "Logged in successfully") {
                _errorMessage.value = "Đăng nhập thành công"
                navController.navigate(Route.NavigatorScreen.route) {
                        popUpTo(0) { inclusive = true }
                }
            } else {
                _errorMessage.value = result
            }
        }
    }
}