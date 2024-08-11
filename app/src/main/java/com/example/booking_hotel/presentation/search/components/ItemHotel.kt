package com.example.booking_hotel.presentation.search.components

import android.R.attr.maxLines
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.booking_hotel.R
import com.example.booking_hotel.domain.model.Property
import com.example.booking_hotel.ui.theme.Color_4B5842
import com.example.booking_hotel.ui.theme.Color_986601
import com.example.booking_hotel.ui.theme.TextColor


@Composable
fun ItemHotel(
    property: Property? = null,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val context = LocalContext.current
    val images = property?.images
    val ratePerNightX = property?.rate_per_night
    ConstraintLayout(
        modifier = modifier.fillMaxWidth().clickable { onClick.invoke() }
    ) {
        property?.let {
            val (image, name, rate, price) = createRefs()
            AsyncImage(
                model = ImageRequest.Builder(context).data(images?.get(0)?.original_image).build(),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .shadow(8.dp)
                    .constrainAs(image) {
                        start.linkTo(parent.start)
                    },
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .constrainAs(name) {
                        start.linkTo(image.end)
                        top.linkTo(parent.top)
                    }
                    .padding(start = 10.dp)
            ) {
                Text(
                    text = property.name, style = TextStyle(
                        color = TextColor,
                        fontFamily = FontFamily(Font(R.font.lato_bold)),
                        fontSize = 16.sp
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = property.description ?: "", style = TextStyle(
                        color = Color.Black,
                        fontFamily = FontFamily(Font(R.font.lato_regular)),
                        fontSize = 14.sp,
                        fontStyle = FontStyle.Italic
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
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
                    .background(Color_986601)
            ) {
                Text(
                    text = property.overall_rating.toString()+"/5" ?: "N/A", style = TextStyle(
                        color = Color.White,
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.lato_regular))
                    ),
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                        .padding(vertical = 2.dp)
                )
            }
            Column(
                modifier = Modifier
                    .constrainAs(price) {
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
            ) {
                Text(
                    text = "Giá chỉ từ", style = TextStyle(
                        color = Color_4B5842,
                        fontFamily = FontFamily(Font(R.font.lato_regular)),
                        fontSize = 12.sp
                    )
                )
                Text(
                    text = ratePerNightX!!.lowest, style = TextStyle(
                        color = Color_986601,
                        fontFamily = FontFamily(Font(R.font.lato_bold)),
                        fontSize = 18.sp,
                    )
                )
            }
        }
    }
}