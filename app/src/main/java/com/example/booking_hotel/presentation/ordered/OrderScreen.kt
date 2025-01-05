package com.example.booking_hotel.presentation.ordered

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun OrderScreen(
    viewModel: OrderViewModel = hiltViewModel()
) {
    Button(onClick = { viewModel.getListOrder() }) {
        Text(text = "test")
    }
}