package com.example.booking_hotel.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.booking_hotel.R
import com.example.booking_hotel.ui.theme.ContentColor
import com.example.booking_hotel.ui.theme.TextColor
import com.example.booking_hotel.ui.theme.TitleColor

@Composable
fun CheckWidget(
    modifier: Modifier = Modifier,
    check: String,
    date: String
) {
    ConstraintLayout(
        modifier = modifier
            .border(
                width = 2.dp,
                color = Color(0xFF757575),
                shape = RoundedCornerShape(4.dp)
            )
            .padding(end = 35.dp)
            .padding(vertical = 6.dp)
    ) {
        val (icon, line, checkSelection, checkDate) = createRefs()
        Image(
            painterResource(id = R.drawable.ic_calendar), contentDescription = null,
            modifier = Modifier
                .constrainAs(icon) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .padding(start = 10.dp),
        )
        Divider(
            color = TextColor,
            modifier = Modifier
                .constrainAs(line) {
                    start.linkTo(icon.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .height(28.dp)
                .width(10.dp)
                .padding(start = 8.dp)
        )
        Text(
            text = check, style = TextStyle(
                color = TitleColor,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.lato_regular))
            ),
            modifier = Modifier
                .constrainAs(checkSelection) {
                    top.linkTo(parent.top)
                    start.linkTo(line.end)
                    bottom.linkTo(checkDate.top)
                    end.linkTo(parent.end)
                }
        )
        Text(
            text = date, style = TextStyle(
                color = ContentColor,
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.lato_regular))
            ),
            modifier = Modifier
                .constrainAs(checkDate) {
                    top.linkTo(checkSelection.bottom)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(line.end)
                    end.linkTo(parent.end)
                }
                .padding(start = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CheckWidgetPreview() {
    CheckWidget(check = "saf", date = "asfafa")
}