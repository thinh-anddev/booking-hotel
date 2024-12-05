package com.example.booking_hotel.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.booking_hotel.domain.model.Hotel
import com.example.booking_hotel.presentation.home.components.ItemHotSearch
import com.example.booking_hotel.presentation.search.handlePagingResult

@Composable
fun HotelSearchList(
    modifier: Modifier = Modifier,
    properties: LazyPagingItems<Hotel>
) {
    println("list size: ${properties.itemCount}")
    val handlePagingResult = handlePagingResult(properties = properties)
    if (handlePagingResult) {
        LazyRow(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = 7.dp,
                end = 21.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            items(
                count = properties.itemCount
            ) {
                properties[it].let { property ->
                    if (property != null) {
                        ItemHotSearch(hotel = property)
                    }
                }
            }
        }
    }
}