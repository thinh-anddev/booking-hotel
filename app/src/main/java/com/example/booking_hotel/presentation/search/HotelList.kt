package com.example.booking_hotel.presentation.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.booking_hotel.domain.model.Property
import com.example.booking_hotel.presentation.search.components.ItemHotel

@Composable
fun HotelList(
    modifier: Modifier = Modifier,
    properties: LazyPagingItems<Property>
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
            count = properties.itemCount
        ) {
            properties[it].let {
                property ->
                if (property != null) {
                    ItemHotel(property = property)
                }
            }
        }
    }
}