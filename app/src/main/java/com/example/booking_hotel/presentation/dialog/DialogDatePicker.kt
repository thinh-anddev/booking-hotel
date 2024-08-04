package com.example.booking_hotel.presentation.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogDatePicker(
    modifier: Modifier = Modifier,
    showDialog: Boolean? = false,
    dateState: DatePickerState
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (showDialog == true) {
            DatePickerDialog(
                onDismissRequest = { showDialog == false },
                confirmButton = {
                    Button(
                        onClick = { showDialog == false }
                    ) {
                        Text(text = "Ok")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = { showDialog == false }
                    ) {
                        Text(text = "Cancel")
                    }
                }
            ) {
                DatePicker(state = dateState, showModeToggle = true)
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun DatePickerDialogPreview() {
    val dateState = rememberDatePickerState()
    DialogDatePicker(showDialog = true, dateState = dateState)
}