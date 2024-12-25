package com.example.booking_hotel.helper

object Constant {
    //emulator (check ipconfig)
    //pc: 192.168.102.9
    //laptop: 192.168.5.172
    const val BASE_URL = "http://192.168.5.172:8081/"
    const val PROPERTY = "PROPERTY"

    //search hotel
    const val API_GET_HOTEL = "api/hotels/search"
    //get list rating
    const val RATINGS = "ratings"
    const val AVGRATE = "rateAvg"
    const val API_HOTEL = "api/hotels"
    const val TOTAL_RATE = "totalRate"
    const val COUNT_STAR = "countStar"
}