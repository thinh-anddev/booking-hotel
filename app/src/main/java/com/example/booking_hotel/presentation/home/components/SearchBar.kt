package com.example.booking_hotel.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.booking_hotel.R

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    text: String,
    onClick: (() -> Unit)? = null,
    placeHolder: String,
    onValueChange: (String) -> Unit
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

    ConstraintLayout(
        modifier = modifier
    ) {
        val (textField, image) = createRefs()
        OutlinedTextField(
            value = text,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = placeHolder, style = TextStyle(
                        color = Color(0xFF757575)
                    )
                )
            },
            singleLine = true,
            modifier = Modifier
                .constrainAs(textField) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                }
                .fillMaxWidth()
                .border(
                    width = 2.dp,
                    color = Color(0xFF757575),
                    shape = RoundedCornerShape(4.dp)
                )
        )

        Image(painterResource(id = R.drawable.ic_location), contentDescription = null,
            modifier = Modifier
                .constrainAs(image) {
                    top.linkTo(textField.top)
                    bottom.linkTo(textField.bottom)
                    end.linkTo(textField.end)
                }
                .padding(end = 15.dp))
    }


}
