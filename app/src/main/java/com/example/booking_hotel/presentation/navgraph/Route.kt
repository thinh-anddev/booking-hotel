package com.example.booking_hotel.presentation.navgraph

sealed class Route(
    val route: String
) {
    data object SplashScreen : Route("SplashScreen")
    data object IntroScreen : Route("IntroScreen")
    data object RegisterScreen : Route("RegisterScreen")
    data object LoginScreen : Route("LoginScreen")
    data object HomeScreen : Route("HomeScreen")
    data object ExploreScreen : Route("ExploreScreen")
    data object OrderScreen : Route("OrderScreen")
    data object AccountScreen : Route("AccountScreen")
    data object NavigatorScreen : Route("NavigatorScreen")
}