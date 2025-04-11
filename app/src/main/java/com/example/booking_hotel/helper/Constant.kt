package com.example.booking_hotel.helper

object Constant {
    //emulator (check ipconfig)
    const val BASE_URL = "http://10.50.224.139:8081/"
    const val PROPERTY = "PROPERTY"

    //search hotel
    const val API_GET_HOTEL = "api/hotels/search"
    //get list rating
    const val RATINGS = "ratings"
    const val AVGRATE = "rateAvg"
    const val API_HOTEL = "api/hotels"
    const val TOTAL_RATE = "totalRate"
    const val COUNT_STAR = "countStar"
    const val GET_HOTEL = "getHotel"
    //user
    const val AUTH = "api/auth"
    const val REGISTER = "register"
    const val LOGIN = "login"
    const val GET_USER = "getUser"
    const val UPDATE_USER = "updateUser"
    const val CHANGE_PASSWORD = "changePassword"
    const val FORGOT_PASSWORD = "forgot-password"
    const val SEND_OTP = "send-otp"
    //order
    const val ORDER = "api/order"
    const val SAVE_ORDER = "saveOrder"
    const val GET_ORDER = "getOrder"
    const val GET_LIST_ORDER = "getListOrder"
    const val UPDATE_ORDER_STATUS = "updateOrderStatus"
    //revenue
    const val REVENUE="api/revenue"
    const val GET_REVENUE_BY_MONTH="getRevenueByMonth"

    //status order
    const val PENDING = "PENDING"
    const val PAID = "PAID"
    const val CANCELED = "CANCELED"
    const val CHECK_OUT = "CHECK_OUT"
}