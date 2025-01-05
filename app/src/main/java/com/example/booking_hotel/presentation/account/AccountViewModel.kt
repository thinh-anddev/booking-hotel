package com.example.booking_hotel.presentation.account

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.compose.composable
import androidx.navigation.createGraph
import com.example.booking_hotel.data.remote.dto.ChangePasswordRequest
import com.example.booking_hotel.data.remote.dto.GetUserResponse
import com.example.booking_hotel.data.remote.dto.UpdateUserRequest
import com.example.booking_hotel.domain.model.User
import com.example.booking_hotel.domain.repository.UserRepository
import com.example.booking_hotel.helper.SharedPreferencesHelper
import com.example.booking_hotel.presentation.navgraph.Route
import com.example.booking_hotel.presentation.splash.SplashScreen
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

    private var _password = MutableLiveData("")
    val password: LiveData<String> = _password

    private var _newPassword = MutableLiveData("")
    val newPassword: LiveData<String> = _newPassword

    private var _confirmPassword = MutableLiveData("")
    val confirmPassword: LiveData<String> = _confirmPassword

    private var _contact = MutableLiveData("")
    val contact: LiveData<String> = _contact

    private var _email = MutableLiveData("")
    val email: LiveData<String> = _email

    private var _avatar = MutableLiveData("")
    val avatar: LiveData<String> = _avatar

    private var _errorValue = MutableLiveData("")
    val errorValue: LiveData<String> = _errorValue
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
                _email.value = user.email
            } else {
                _avatar.value = ""
                _name.value = ""
                _contact.value = ""
                _email.value = ""
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
    fun updateUser(navController: NavController) {
        viewModelScope.launch {
            val updateUserRequest = UpdateUserRequest(
                contact = _contact.value!!,
                email = _email.value!!,
                avatar = _avatar.value!!
            )
            userRepository.updateUser(sharedPreferencesHelper.getUserId(), updateUserRequest)
            navController.navigate(Route.AccountScreen.route) {
                popUpTo(0) { inclusive = false}
            }
        }
    }
    fun onImageUriSelected(uri: Uri) {
        _avatar.value = uri.toString()
    }
    fun logout(navController: NavController) {
        sharedPreferencesHelper.logout()
        navController.navigate(Route.IntroScreen.route) {
            popUpTo(navController.graph.startDestinationId) { inclusive = true }
            launchSingleTop = true
        }
    }
    fun onContactChange(contact: String) {
        _contact.value = contact
    }
    fun onEmailChange(email: String) {
        _email.value = email
    }
    fun onPasswordChange(password: String) {
        _password.value = password
    }
    fun onNewPasswordChange(newPassword: String) {
        _newPassword.value = newPassword
    }
    fun onConfirmPasswordChange(confirmPassword: String) {
        _confirmPassword.value = confirmPassword
    }
    fun changePassword(navController: NavController) {
        viewModelScope.launch {
            val changePasswordRequest = ChangePasswordRequest(
                password = _password.value.toString(),
                confirmPassword = _confirmPassword.value.toString(),
                newPassword = _newPassword.value.toString()
                )
            if (_confirmPassword.value == "" || _newPassword.value == "" || _password.value == "") {
                _errorValue.value = "Vui long nhap du thong tin"
            } else {
                val response = userRepository.changePassword(sharedPreferencesHelper.getUserId(), changePasswordRequest)
                if (response == "successfully") {
                    _errorValue.value = "Change password success"
                    navController.navigate(Route.AccountScreen.route) {
                        popUpTo(0) { inclusive = false}
                    }
                } else {
                    _errorValue.value = response
                }
            }
        }
    }
}