package com.example.booking_hotel.di

import com.example.booking_hotel.data.remote.OrderAPI
import com.example.booking_hotel.data.remote.UserAPI
import com.example.booking_hotel.data.repository.OrderRepositoryImpl
import com.example.booking_hotel.domain.repository.OrderRepository
import com.example.booking_hotel.helper.Constant
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OrderModule {
    @Provides
    @Singleton
    fun provideApiInstance(OkHttpClient: OkHttpClient, gson: Gson): OrderAPI {
        return Retrofit
            .Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(OkHttpClient)
            .build()
            .create(OrderAPI::class.java)
    }
    @Provides
    @Singleton
    fun provideOrderRepository(
        orderApi: OrderAPI
    ): OrderRepository {
        return OrderRepositoryImpl(orderApi)
    }
}