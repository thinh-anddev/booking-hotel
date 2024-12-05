package com.example.booking_hotel.domain.model

import java.io.Serializable

data class Hotel(
    val id: Long,
    val type: String?,
    val name: String?,
    val link: String?,
    val checkInTime: String?,
    val checkOutTime: String?,
    val hotelClass: String?,
    val extractedHotelClass: Int?,
    val overallRating: Double?,
    val reviews: Int?,
    val locationRating: Double?,
    val propertyToken: String?,
    val serpapiPropertyDetailsLink: String?,
    val gpsCoordinates: GPSCoordinates?,
    val ratePerNight: Rate?,
    val amenities: List<String>?,
    val nearbyPlaces: List<NearbyPlace>?,
    val images: List<Image>?,
    val ratings: List<Rating>?,
    val reviewsBreakdown: List<ReviewBreakdown>?
): Serializable