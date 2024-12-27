package com.example.booking_hotel.presentation.navgraph

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.booking_hotel.presentation.home.HomeScreen
import com.example.booking_hotel.presentation.intro.IntroScreen
import com.example.booking_hotel.presentation.login.LoginScreen
import com.example.booking_hotel.presentation.navigator.NavigatorScreen
import com.example.booking_hotel.presentation.register.RegisterScreen
import com.example.booking_hotel.presentation.search.SearchScreen
import com.example.booking_hotel.presentation.splash.SplashScreen
import com.example.booking_hotel.presentation.splash.SplashViewModel

@RequiresApi(Build.VERSION_CODES.O)
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
            RegisterScreen(navController = navController)
        }
        composable(
            route = Route.LoginScreen.route
        ) {
            LoginScreen(navController = navController)
        }
        composable(
            route = Route.NavigatorScreen.route
        ) {
            NavigatorScreen()
        }
    }
}