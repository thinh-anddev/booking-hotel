package com.example.booking_hotel.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.booking_hotel.R
import com.example.booking_hotel.ui.theme.Color_757575
import com.example.booking_hotel.ui.theme.ContentColor
import com.example.booking_hotel.ui.theme.TitleColor
import kotlin.io.encoding.Base64
import kotlin.random.Random

@Composable
fun NumberPeopleWidget(
    adults: String,
    childrens: String,
    onClick: (() -> Unit)? = null,
    plusAdult: () -> Unit,
    plusChildren: () -> Unit,
    minusAdult: () -> Unit,
    minusChildren: () -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }

    val isClicked = interactionSource.collectIsPressedAsState().value

    LaunchedEffect(key1 = isClicked) {
        if (isClicked) {
            onClick?.invoke()
        }
    }
    Row(
        modifier = modifier
            .border(
                width = 2.dp,
                color = Color(0xFF757575),
                shape = RoundedCornerShape(4.dp)
            )
            .padding(vertical = 5.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = "Người lớn", style = TextStyle(
                    color = TitleColor,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.lato_regular))
                )
            )
            PeopleCount(plus = plusAdult, minus = minusAdult, people = adults)
        }
        Divider(
            color = ContentColor,
            modifier = Modifier
                .width(2.dp)
                .height(35.dp)
        )
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = "Trẻ em", style = TextStyle(
                    color = TitleColor,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.lato_regular))
                )
            )
            PeopleCount(plus = plusChildren, minus = minusChildren, people = childrens)
        }
    }
}
