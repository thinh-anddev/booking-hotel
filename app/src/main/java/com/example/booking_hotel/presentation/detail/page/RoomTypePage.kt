package com.example.booking_hotel.presentation.detail.page

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.booking_hotel.domain.model.Hotel

@Composable
fun RoomTypePage(
    hotel: Hotel,
    viewModel: PageViewMode = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    LaunchedEffect(key1 = true) {
        val list = viewModel.getListRating(hotel.id)
        Log.d("tesst", list.toString())
    }
}