package com.example.booking_hotel.di

import android.content.Context
import com.example.booking_hotel.helper.SharedPreferencesHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HelperModule {
    @Provides
    @Singleton
    fun provideSharePreference(
        @ApplicationContext context: Context
    ): SharedPreferencesHelper {
        return SharedPreferencesHelper(context)
    }
}