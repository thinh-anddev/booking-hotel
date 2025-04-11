package com.example.booking_hotel.presentation.admin

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AdminScreen(
    navController: NavController,
    modifier:Modifier=Modifier
){
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier=Modifier.fillMaxSize()
        ) {
            Text(text = "hello")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun AdminScreenPreview() {
    val navController = rememberNavController()
    AdminScreen(navController = navController)
}