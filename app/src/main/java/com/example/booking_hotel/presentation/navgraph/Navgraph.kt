package com.example.booking_hotel.presentation.navgraph

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.booking_hotel.domain.model.User
import com.example.booking_hotel.presentation.admin.AdminScreen
import com.example.booking_hotel.presentation.admin.revenue.RevenueScreen
import com.example.booking_hotel.presentation.forgot_password.ForgotPasswordScreen
import com.example.booking_hotel.presentation.home.HomeScreen
import com.example.booking_hotel.presentation.intro.IntroScreen
import com.example.booking_hotel.presentation.login.LoginScreen
import com.example.booking_hotel.presentation.navigator.NavigatorScreen
import com.example.booking_hotel.presentation.qrcode_scanner.QRCodeScanner
import com.example.booking_hotel.presentation.register.RegisterScreen
import com.example.booking_hotel.presentation.register.confirm_otp.ConfirmOTPScreen
import com.example.booking_hotel.presentation.search.SearchScreen
import com.example.booking_hotel.presentation.splash.SplashScreen
import com.example.booking_hotel.presentation.splash.SplashViewModel
import com.google.gson.Gson

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value

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
            route = Route.QRCodeScanner.route
        ) {
            QRCodeScanner(navController = navController)
        }
        composable(
            route = Route.ForgotPasswordScreen.route
        ) {
            ForgotPasswordScreen(navController = navController)
        }
        composable(
            route = Route.ConFirmOTPScreen.route
        ) {
            val otpCode = backStackState?.arguments?.getString("otpCode").toString()
            val jsonUser = backStackState?.arguments?.getString("user").toString()
            val user = Gson().fromJson(jsonUser, User::class.java)

            ConfirmOTPScreen(
                navController = navController, otpCode = otpCode, user = user
            )
        }
        composable(
            route = Route.NavigatorScreen.route
        ) {
            NavigatorScreen()
        }
    }
}