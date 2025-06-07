package com.example.booking_hotel.di

import com.example.booking_hotel.data.remote.HotelAPI
import com.example.booking_hotel.data.remote.UploadApi
import com.example.booking_hotel.data.repository.ImageRepositoryImpl
import com.example.booking_hotel.domain.repository.ImageRepository
import com.example.booking_hotel.helper.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ImageModule {
    @Provides
    @Singleton
    fun provideApiInstance(OkHttpClient: OkHttpClient): UploadApi {
        return Retrofit
            .Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient)
            .build()
            .create(UploadApi::class.java)
    }
    @Provides
    @Singleton
    fun provideImageRepository(
        api:UploadApi
    ):ImageRepository{return ImageRepositoryImpl(api)}
}