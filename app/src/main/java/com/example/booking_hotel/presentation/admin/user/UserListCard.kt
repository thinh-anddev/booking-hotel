package com.example.booking_hotel.presentation.admin.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton

import androidx.compose.runtime.getValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.booking_hotel.domain.model.User
import com.example.booking_hotel.presentation.admin.AdminViewModel

@Composable
fun UserListScreen(
    navController: NavController,
    viewModel: AdminViewModel=hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val listUser by viewModel.listUser.observeAsState(emptyList())
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Danh sách người dùng") },
                navigationIcon = {
                    IconButton(onClick = {navController.popBackStack()}) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            modifier = modifier
                .fillMaxSize()
                .background(Color(0xFFF7F7F7))
                .padding(8.dp)
        ) {
            items(count=listUser.size) {
                UserCard(user = listUser[it])
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
