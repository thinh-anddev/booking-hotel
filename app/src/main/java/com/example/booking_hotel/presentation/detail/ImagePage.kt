package com.example.booking_hotel.presentation.detail

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.booking_hotel.domain.model.Image
import com.example.booking_hotel.domain.model.Property

@Composable
fun ImagePage(
    images: Image,
    context: Context,
    modifier: Modifier = Modifier
) {
    images?.let {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(240.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context).data(it.original_image).build(),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
                    .clip(RoundedCornerShape(4.dp))
                    .shadow(8.dp),
                contentScale = ContentScale.Inside
            )
        }
    }
}