package com.example.booking_hotel.domain.repository

import android.net.Uri
import com.example.booking_hotel.data.remote.dto.UploadReponse
import java.io.File

interface ImageRepository {
suspend fun uploadImage(file:File):UploadReponse
}