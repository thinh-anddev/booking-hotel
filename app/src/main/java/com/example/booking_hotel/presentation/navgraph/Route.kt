package com.example.booking_hotel.presentation.navgraph

sealed class Route(
    val route: String
) {
    object SplashScreen : Route("SplashScreen")
    object IntroScreen : Route("IntroScreen")
    object RegisterScreen : Route("RegisterScreen")
    object LoginScreen : Route("LoginScreen")
    object HomeScreen : Route("HomeScreen")
    object ExploreScreen : Route("ExploreScreen")
    object OrderScreen : Route("OrderScreen")
    object AccountScreen : Route("AccountScreen")
    object NavigatorScreen : Route("NavigatorScreen")
    object DetailScreen : Route("DetailScreen")
    object SearchScreen : Route("SearchScreen/{searchQuery}/{checkInDate}/{checkOutDate}/{adult}/{children}") {
        fun passData(
            searchQuery: String,
            checkInDate: String,
            checkOutDate: String,
            adult: String,
            children: String,
        ): String {
            return "SearchScreen/$searchQuery/$checkInDate/$checkOutDate/$adult/$children"
        }
    }
}