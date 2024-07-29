package com.example.booking_hotel.di

import android.util.Log
import com.example.booking_hotel.data.remote.HotelAPI
import com.example.booking_hotel.data.repository.HotelRepositoryImpl
import com.example.booking_hotel.domain.repository.HotelRepository
import com.example.booking_hotel.domain.usecase.SearchHotelUsecase
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
object NetworkModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiInstance(OkHttpClient: OkHttpClient): HotelAPI {
        return Retrofit
            .Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient)
            .build()
            .create(HotelAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideHotelRepository(
        hotelAPI: HotelAPI
    ): HotelRepository {
        Log.d("API_TEST", "Starting API call")
        return HotelRepositoryImpl(hotelAPI)
    }

    @Provides
    @Singleton
    fun provideUsecase(
        hotelRepository: HotelRepository
    ): SearchHotelUsecase {
        return SearchHotelUsecase(
            hotelRepository = hotelRepository
        )
    }
}