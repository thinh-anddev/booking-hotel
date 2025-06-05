package com.example.booking_hotel.presentation.admin.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.booking_hotel.domain.model.Hotel
import com.example.booking_hotel.presentation.search.components.ItemHotel
import com.example.booking_hotel.presentation.search.handlePagingResult

@Composable
fun HotelListAdmin(
    modifier: Modifier = Modifier,
    hotels: List<Hotel>,
    onClick: (Hotel) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            start = 7.dp,
            end = 21.dp
        ),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            count = hotels.size
        ) {
            hotels[it].let { property ->
                if (property != null) {
                    ItemHotel(hotel = property, onClick = {
                        onClick(property)
                    })
                }
            }
        }
    }
}
