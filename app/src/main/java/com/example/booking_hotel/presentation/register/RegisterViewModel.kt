package com.example.booking_hotel.presentation.register

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.booking_hotel.domain.model.User
import com.example.booking_hotel.domain.repository.UserRepository
import com.example.booking_hotel.presentation.navgraph.Route
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _username = mutableStateOf("")
    val username: State<String> = _username

    private val _email = mutableStateOf("")
    val email: State<String> = _email

    private val _contact = mutableStateOf("")
    val contact: State<String> = _contact

    private val _password = mutableStateOf("")
    val password: State<String> = _password

    private val _confirmPassword = mutableStateOf("")
    val confirmPassword: State<String> = _confirmPassword
    private var _otpCode = MutableLiveData("")
    val otpCode: LiveData<String> = _otpCode

    private val _errorMessage = mutableStateOf("")
    val errorMessage: State<String> = _errorMessage

    fun onUsernameChange(username: String) {
        _username.value = username
    }

    fun onEmailChange(email: String) {
        _email.value = email
    }

    fun onContactChange(contact: String) {
        _contact.value = contact
    }

    fun onPasswordChange(password: String) {
        _password.value = password
    }

    fun onConfirmPasswordChange(confirmPassword: String) {
        _confirmPassword.value = confirmPassword
    }

    fun onOTPCodeChange(otpCode: String) {
        _otpCode.value = otpCode
    }

    fun register(navController: NavController, user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = userRepository.registerUser(user)
            if (result == "User registered successfully") {
                withContext(Dispatchers.Main) {
                    _errorMessage.value = "Đăng ký thành công"
                    navController.navigate(Route.LoginScreen.route) {
                        popUpTo(0) { inclusive = false }
                    }
                }
            } else {
                withContext(Dispatchers.Main) {
                    Log.d("dddd", result)
                    _errorMessage.value = result
                }
            }
        }
    }

    fun sendOTP(navController: NavController) {
        viewModelScope.launch(Dispatchers.IO) {
            if (_email.value.isBlank()
                || _contact.value.isBlank()
                || _password.value.isBlank()
                || _confirmPassword.value.isBlank()
                || _username.value.isBlank()
            ) {
                _errorMessage.value = "Vui lòng nhập đầy đủ thông tin"
            } else if (_password.value != _confirmPassword.value) {
                _errorMessage.value = "Mật khẩu không trùng khớp"
            } else if (_password.value.length <= 8) {
                _errorMessage.value = "Hãy nhập mật khẩu dài hơn"
            } else {
                val user = User(
                    userName = _username.value,
                    password = _password.value,
                    email = _email.value,
                    contact = _contact.value,
                    age = 30,
                    avatar = "",
                    dateCreated = "",
                    role="USER"
                )
                val gson = Gson()
                val jsonUser = gson.toJson(user)
                val response = userRepository.sendOTP(user)
                val message = response?.message
                if (message == "successfully") {
                    withContext(Dispatchers.Main) {
                        _errorMessage.value = "Vui lòng nhập mã OTP"
                        navController.navigate(
                            Route.ConFirmOTPScreen.passData(
                                otpCode = response.otpCode!!,
                                user = jsonUser
                            )
                        ) {
                            popUpTo(0) { inclusive = false }
                        }
                    }
                } else {
                    _errorMessage.value = "error"
                }
            }
        }
    }
}