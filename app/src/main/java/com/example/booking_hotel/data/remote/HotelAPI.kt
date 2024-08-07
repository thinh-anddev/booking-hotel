package com.example.booking_hotel.data.remote

import com.example.booking_hotel.data.remote.dto.HotelResponse
import com.example.booking_hotel.helper.Constant
import retrofit2.http.GET
import retrofit2.http.Query

interface HotelAPI {
    @GET("search.json")
    suspend fun getHotels(
        @Query("engine") engine: String = "google_hotels",
        @Query("currency") currency: String = "VND",
        @Query("gl") gl: String = "vn",
        @Query("hl") hl: String = "vi",
        @Query("children_ages") childrenAge: String = "5",
        @Query("check_in_date") checkInDate: String,
        @Query("check_out_date") checkOutDate: String,
        @Query("adults") adults: String,
        @Query("children") children: String,
        @Query("q") searchQuery: String,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = Constant.API_KEY,
        @Query("sort_by") sortBy: String
    ): HotelResponse
}