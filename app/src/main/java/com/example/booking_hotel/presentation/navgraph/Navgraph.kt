package com.example.booking_hotel.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.booking_hotel.presentation.intro.IntroScreen
import com.example.booking_hotel.presentation.register.RegisterScreen
import com.example.booking_hotel.presentation.splash.SplashScreen
import com.example.booking_hotel.presentation.splash.SplashViewModel

@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        composable(
            Route.SplashScreen.route
        ) {
            val viewModel: SplashViewModel = hiltViewModel()
            SplashScreen(viewModel, navController)
        }
        composable(
            route = Route.IntroScreen.route
        ) {
            IntroScreen(navController = navController)
        }
        composable(
            route = Route.RegisterScreen.route
        ) {
            RegisterScreen()
        }
    }
}