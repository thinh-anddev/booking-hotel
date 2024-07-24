package com.example.booking_hotel.presentation.intro.components

import android.graphics.Color
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun PageIndicator(
    modifier: Modifier = Modifier,
    pageSize: Int,
    selectedPage: Int,
    @DrawableRes selectedBackground: Int,
    @DrawableRes unSelectedBackground: Int
) {
    Row(
        modifier = modifier.width(70.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        repeat(
            times = pageSize
        ) { page ->
            Box(
                modifier = Modifier
            ) {
                Image(
                    painter = painterResource(id = if (page == selectedPage) selectedBackground else unSelectedBackground),
                    contentDescription = null
                )
            }
        }
    }
}