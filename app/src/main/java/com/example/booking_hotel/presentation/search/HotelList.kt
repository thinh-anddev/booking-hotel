package com.example.booking_hotel.presentation.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.booking_hotel.domain.model.Hotel
import com.example.booking_hotel.presentation.common.EmptyScreen
import com.example.booking_hotel.presentation.common.ShimmerEffect
import com.example.booking_hotel.presentation.search.components.ItemHotel

@Composable
fun HotelList(
    modifier: Modifier = Modifier,
    properties: LazyPagingItems<Hotel>,
    onClick: (Hotel) -> Unit
) {
    val handlePagingResult = handlePagingResult(properties = properties)

    if (handlePagingResult) {
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
                properties[it].let { property ->
                    if (property != null) {
                        ItemHotel(hotel = property, onClick = {
                            onClick(property)
                        })
                    }
                }
            }
        }
    }
}

@Composable
fun handlePagingResult(
    properties: LazyPagingItems<Hotel>
): Boolean {
    val loadState = properties.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }

        error != null -> {
            EmptyScreen(error = error)
            false
        }

        else -> {
            true
        }
    }
}