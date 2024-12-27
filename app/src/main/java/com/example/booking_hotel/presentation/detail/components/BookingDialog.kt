package com.example.booking_hotel.presentation.detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Dialog
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Remove
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.booking_hotel.ui.theme.TextColor


@Composable
fun BookingDialog(
    showDialog: Boolean,
    adult: Int,
    children: Int,
    numberNight: Int,
    onDismiss: () -> Unit,
    onConfirm: (Int, Int, Int) -> Unit
) {
    var adultVal by remember { mutableStateOf(adult) }
    var childrenVal by remember {
        mutableStateOf(children)
    }
    var numberNightVal by remember {
        mutableStateOf(numberNight)
    }
    if (showDialog) {
        Dialog(onDismissRequest = onDismiss) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = Color.White,
                modifier = Modifier.padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        IconButton(onClick = { onDismiss() } ) {
                            Icon(Icons.Default.Close, contentDescription = "Cancel")
                        }
                    }
                    Text("Ngày nhận phòng:", fontWeight = FontWeight.Bold)
                    TextField(
                        value = "06/09/2022",
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = {
                            Icon(Icons.Default.DateRange, contentDescription = "Select Date")
                        }
                    )

                    CounterField(
                        label = "Số đêm:",
                        value = numberNightVal,
                        onValueChange = { numberNightVal = it })
                    CounterField(
                        label = "Người lớn:",
                        value = adultVal,
                        onValueChange = { adultVal = it })
                    CounterField(
                        label = "Trẻ em:",
                        value = childrenVal,
                        onValueChange = { childrenVal = it })

                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            onClick = { onConfirm(numberNightVal, adultVal, childrenVal) },
                            modifier = Modifier.padding(horizontal = 20.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = TextColor,
                                contentColor = Color.White
                            )
                        ) {
                            Text("Xác nhận")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CounterField(label: String, value: Int, onValueChange: (Int) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(label)
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { if (value > 0) onValueChange(value - 1) }) {
                Icon(Icons.Default.Remove, contentDescription = "Decrease")
            }
            Text(value.toString(), textAlign = TextAlign.Center)
            IconButton(onClick = { onValueChange(value + 1) }) {
                Icon(Icons.Default.Add, contentDescription = "Increase")
            }
        }
    }
}
