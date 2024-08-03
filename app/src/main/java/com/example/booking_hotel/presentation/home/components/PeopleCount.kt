package com.example.booking_hotel.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.booking_hotel.R
import com.example.booking_hotel.ui.theme.Color_757575

@Composable
fun PeopleCount(
    modifier: Modifier = Modifier,
    plus: () -> Unit,
    minus: () -> Unit,
    people: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick =  plus) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "addIcon")
        }
        Text(
            text = people, style = TextStyle(
                color = Color_757575,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.lato_regular))
            )
        )
        IconButton(onClick = minus) {
            Icon(imageVector = Icons.Default.Close, contentDescription = "clear")
        }
    }
}
//@Preview(showBackground =true)
//@Composable
//fun PeopleCountPreview() {
//    PeopleCount()
//}
