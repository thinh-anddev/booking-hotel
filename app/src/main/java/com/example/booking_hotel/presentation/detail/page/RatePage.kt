package com.example.booking_hotel.presentation.detail.page

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.booking_hotel.domain.model.Hotel

@Composable
fun RatePage(
    hotel: Hotel,
    viewModel: PageViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val avgRating by viewModel.avgRating.observeAsState()
    Text(text = "$avgRating")
}