package com.example.booking_hotel.presentation.navigator.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.booking_hotel.R

@Composable
fun BottomNavigator(
    items: List<BottomNavigatorItem>,
    selected: Int,
    onItemClick: (Int) -> Unit
) {
    Box {
        Divider(
            color = Color(0xFFA8A8A8),
            thickness = 1.dp
        )
        NavigationBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp),
            containerColor = Color.White
        ) {
            items.forEachIndexed { index, item ->
                NavigationBarItem(
                    selected = index == selected,
                    onClick = { onItemClick(index) },
                    icon = {
                        val icon = if (index == selected) item.iconSelected else item.iconUnselected
                        val textColor = if (index == selected) Color(0xFF2C3922) else Color.Black
                        Column(
                            modifier = Modifier,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                painterResource(id = icon),
                                contentDescription = null
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(
                                text = item.name, style = TextStyle(
                                    color = textColor,
                                    fontFamily = FontFamily(Font(R.font.lato_regular)),
                                    fontSize = 12.sp
                                )
                            )
                            if (index == selected) {
                                Image(painterResource(id = R.drawable.bottom_line), contentDescription = null)
                            }
                        }
                    }
                )
            }
        }
    }
}

data class BottomNavigatorItem(
    @DrawableRes val iconSelected: Int,
    @DrawableRes val iconUnselected: Int,
    val name: String
)