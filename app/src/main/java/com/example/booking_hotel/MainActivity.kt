package com.example.booking_hotel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.navigation.NavGraph
import com.example.booking_hotel.presentation.navgraph.NavGraph
import com.example.booking_hotel.presentation.navgraph.Route
import com.example.booking_hotel.ui.theme.BookinghotelTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window,false)
        setContent {
//            SideEffect {
//                val isSystemInDarkMode = isSystemInDarkTheme()
//                val systemController = rememberSystemUiController()
//                systemController.setSystemBarsColor(
//                    color = Color.Transparent,
//                    darkIcons = !isSystemInDarkMode
//                )
//            }
            //Add fillMaxSize()
            Box(modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()) {
                com.example.booking_hotel.presentation.navgraph.NavGraph(startDestination = Route.SplashScreen.route)
            }
        }
    }
}
