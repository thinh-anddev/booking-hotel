package com.example.booking_hotel.helper

object Constant {
    //emulator (check ipconfig)
    const val BASE_URL = "http://192.168.1.4:8081/"
    const val PROPERTY = "PROPERTY"

    //search hotel
    const val API_GET_HOTEL = "api/hotels/search"
    //get list rating
    const val RATINGS = "ratings"
    const val AVGRATE = "rateAvg"
    const val API_HOTEL = "api/hotels"
    const val TOTAL_RATE = "totalRate"
    const val COUNT_STAR = "countStar"
    //user
    const val AUTH = "api/auth"
    const val REGISTER = "register"
    const val LOGIN = "login"
    //get user
    const val GET_USER = "getUser"
    const val UPDATE_USER = "updateUser"
    const val CHANGE_PASSWORD = "changePassword"
}