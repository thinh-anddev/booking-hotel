package com.example.booking_hotel.presentation.home

import androidx.annotation.DrawableRes
import com.example.booking_hotel.R

data class ExtensionModel(
    @DrawableRes val image: Int
)

val listExtension = listOf(
    ExtensionModel(
        R.drawable.img_nhanhchong
    ),
    ExtensionModel(
        R.drawable.img_dongian
    ),
    ExtensionModel(
        R.drawable.img_uytin
    )
)