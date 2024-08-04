package com.example.booking_hotel.presentation.common

import android.annotation.SuppressLint
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.booking_hotel.ui.theme.Shimmer

@Composable
fun ShimmerEffect(

) {
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        repeat(10) {
            HotelCardEffect()
        }
    }
}

@Composable
fun HotelCardEffect(
    modifier: Modifier = Modifier
) {
    ConstraintLayout(
        modifier = modifier.fillMaxWidth()
    ) {
        val (image, name, rate, price) = createRefs()
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(4.dp))
                .shadow(8.dp)
                .constrainAs(image) {
                    start.linkTo(parent.start)
                }
                .shimmerEffect()
        )
        Column(
            modifier = Modifier
                .constrainAs(name) {
                    start.linkTo(image.end)
                    top.linkTo(parent.top)
                }
                .padding(start = 10.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .shimmerEffect()
                    .height(15.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .shimmerEffect()
                    .height(10.dp)
            )
        }
        Column(
            modifier = Modifier
                .constrainAs(rate) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(image.end)
                }
                .padding(start = 10.dp)
                .clip(RoundedCornerShape(20.dp))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .shimmerEffect()
                    .height(10.dp)
            )
        }
        Column(
            modifier = Modifier
                .constrainAs(price) {
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .shimmerEffect()
                    .height(15.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .shimmerEffect()
                    .height(15.dp)
            )
        }
    }
}

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.shimmerEffect() = composed {
    val transition = rememberInfiniteTransition()
    val alpha = transition.animateFloat(
        initialValue = 0.2f,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    ).value
    background(color = Shimmer.copy(alpha))
}