package com.example.booking_hotel.presentation.search.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.booking_hotel.R
import com.example.booking_hotel.ui.theme.TextColor

@Composable
fun BottomButton(
    modifier: Modifier = Modifier,
    items: List<BottomButtonItem>,
    onItemClick: (Int) -> Unit
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        items.forEachIndexed { index, bottomButtonItem ->
            Button(
                onClick = { onItemClick(index) },
                modifier = Modifier
                    .weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                )
            ) {
                Row(
                    modifier = Modifier
                ) {
                    Image(painterResource(id = bottomButtonItem.icon), contentDescription = null)
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = bottomButtonItem.name, style = TextStyle(
                            color = TextColor,
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.lato_regular))
                        )
                    )
                }
            }
        }

    }
}

data class BottomButtonItem(
    @DrawableRes val icon: Int,
    val name: String
)

val listBottomButton = listOf(
    BottomButtonItem(R.drawable.ic_price, "Giá"),
    BottomButtonItem(R.drawable.ic_most_view, "Xem nhiều")
)

@Preview
@Composable
fun BottomButtonPreview() {

}