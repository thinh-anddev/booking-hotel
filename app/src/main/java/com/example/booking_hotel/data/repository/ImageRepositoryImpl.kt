package com.example.booking_hotel.data.repository

import android.net.Uri
import com.example.booking_hotel.data.remote.UploadApi
import com.example.booking_hotel.data.remote.dto.UploadReponse
import com.example.booking_hotel.domain.repository.ImageRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class ImageRepositoryImpl(
    private val imageApi:UploadApi
):ImageRepository {
    override suspend fun uploadImage(file: File): UploadReponse {
        val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
        val multipartBody = MultipartBody.Part.createFormData(
            name = "file",
            filename = file.name,
            body = requestFile
        )

        val response = imageApi.uploadImage(multipartBody)

        if (response.isSuccessful) {
            return response.body() ?: throw Exception("No response body")
        } else {
            throw Exception("Upload failed: ${response.errorBody()?.string()}")
        }
    }
}