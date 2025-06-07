package com.example.booking_hotel.data.remote

import com.example.booking_hotel.data.remote.dto.UploadReponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface UploadApi {
    @Multipart
    @POST("/api/images/upload")
    suspend fun uploadImage(
        @Part file: MultipartBody.Part
    ): Response<UploadReponse>
}