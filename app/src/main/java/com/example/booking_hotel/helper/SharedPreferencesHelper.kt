package com.example.booking_hotel.helper

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import javax.inject.Inject

class SharedPreferencesHelper @Inject constructor(
    context: Context
) {
    companion object {
        private const val PREFS_NAME = "user_prefs"
        private const val USER_ID = "USER_ID"
        private const val KEY_IS_LOGGED_IN = "isLoggedIn"
    }
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    fun isLogin(): Boolean {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)
    }
    fun saveUserId(id: Long) {
        sharedPreferences.edit()
            .putLong(USER_ID, id)
            .putBoolean(KEY_IS_LOGGED_IN, true)
            .apply()
    }
    fun getUserId(): Long {
        return sharedPreferences.getLong(USER_ID, 1L)
    }
    @SuppressLint("CommitPrefEdits")
    fun logout() {
        sharedPreferences.edit()
            .putBoolean(KEY_IS_LOGGED_IN, false)
            .putLong(USER_ID, 1L).apply()
    }
}