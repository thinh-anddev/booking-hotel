package com.example.booking_hotel.presentation.ordered.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.booking_hotel.domain.model.Order

@Composable
fun OrderList(
    modifier: Modifier = Modifier,
    orderList: List<Order>
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
            count = orderList.size
        ) {
//            ItemOrder(order = orderList[it])
        }
    }
}