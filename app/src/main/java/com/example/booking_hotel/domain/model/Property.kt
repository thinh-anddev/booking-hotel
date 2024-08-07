package com.example.booking_hotel.domain.model

data class Property(
    val amenities: List<String>,
    val check_in_time: String,
    val check_out_time: String,
    val deal: String,
    val deal_description: String,
    val description: String,
    val essential_info: List<String>,
    val excluded_amenities: List<String>,
    val extracted_hotel_class: Int,
    val gps_coordinates: GpsCoordinates,
    val hotel_class: String,
    val images: List<Image>,
    val link: String,
    val location_rating: Double,
    val name: String,
    val nearby_places: List<NearbyPlace>,
    val overall_rating: Double,
    val prices: List<Price>,
    val property_token: String,
    val rate_per_night: RatePerNightX,
    val ratings: List<Rating>,
    val reviews: Int,
    val reviews_breakdown: List<ReviewsBreakdown>,
    val serpapi_property_details_link: String,
    val total_rate: TotalRate,
    val type: String
)

fun List<Property>.sortByPrice(): List<Property> {
    return this.sortedBy { property -> property.rate_per_night.lowest }
}

fun List<Property>.sortByViewRating(): List<Property> {
    return this.sortedByDescending { property -> property.rate_per_night.lowest }
}