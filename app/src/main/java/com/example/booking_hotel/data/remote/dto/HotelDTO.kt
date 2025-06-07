package com.example.booking_hotel.data.remote.dto

data class HotelDTO(
    val type: String,
    val name: String,
    val link: String,
    val checkInTime: String?,
    val checkOutTime: String?,
    val hotelClass: String?,
    val extractedHotelClass: Int?,
    val overallRating: Double?,
    val reviews: Int?,
    val locationRating: Double?,
    val propertyToken: String,
    val serpapiPropertyDetailsLink: String?,
    val remainRooms: Int?,
    val amenities: List<String>?,
    val images: List<ImageDTO>?
)