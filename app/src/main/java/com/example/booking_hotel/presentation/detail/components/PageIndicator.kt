package com.example.booking_hotel.presentation.detail.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun PageIndicatorDetail(
    modifier: Modifier = Modifier,
    pageSize: Int,
    selectedPage: Int,
    @DrawableRes selectedBackground: Int,
    @DrawableRes unSelectedBackground: Int,
    padding: Dp = 4.dp
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        repeat(times = pageSize) { page ->
            Box(
                modifier = Modifier.padding(padding)
            ) {
                Image(
                    painter = painterResource(id = if (page == selectedPage) selectedBackground else unSelectedBackground),
                    contentDescription = null
                )
            }
        }
    }
}