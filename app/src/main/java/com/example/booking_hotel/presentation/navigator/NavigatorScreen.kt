package com.example.booking_hotel.presentation.navigator

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.booking_hotel.R
import com.example.booking_hotel.domain.model.Hotel
import com.example.booking_hotel.helper.Constant
import com.example.booking_hotel.helper.dateToString
import com.example.booking_hotel.helper.getCurrentDate
import com.example.booking_hotel.presentation.account.AccountScreen
import com.example.booking_hotel.presentation.account.change_information.ChangeInformationScreen
import com.example.booking_hotel.presentation.account.change_password.ChangePasswordScreen
import com.example.booking_hotel.presentation.account.contact.ContactScreen
import com.example.booking_hotel.presentation.account.qrcode.QRCodeScreen
import com.example.booking_hotel.presentation.confirm_order.ConfirmOrderScreen
import com.example.booking_hotel.presentation.detail.DetailScreen
import com.example.booking_hotel.presentation.explore.ExploreScreen
import com.example.booking_hotel.presentation.home.HomeScreen
import com.example.booking_hotel.presentation.intro.IntroScreen
import com.example.booking_hotel.presentation.login.LoginScreen
import com.example.booking_hotel.presentation.navgraph.Route
import com.example.booking_hotel.presentation.navigator.components.BottomNavigator
import com.example.booking_hotel.presentation.navigator.components.BottomNavigatorItem
import com.example.booking_hotel.presentation.ordered.OrderScreen
import com.example.booking_hotel.presentation.qrcode_scanner.QRCodeScanner
import com.example.booking_hotel.presentation.register.RegisterScreen
import com.example.booking_hotel.presentation.search.SearchScreen
import com.example.booking_hotel.presentation.search.SearchViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigatorScreen(

) {
    val bottomNavigationItem = remember {
        listOf(
            BottomNavigatorItem(
                R.drawable.ic_home_selected,
                R.drawable.ic_home_unselected,
                "Trang chủ"
            ),
            BottomNavigatorItem(
                R.drawable.ic_explore_selected,
                R.drawable.ic_explore_unselected,
                "Khám phá"
            ),
            BottomNavigatorItem(
                R.drawable.ic_order_selected,
                R.drawable.ic_order_unselected,
                "Đã Đặt"
            ),
            BottomNavigatorItem(
                R.drawable.ic_account_selected,
                R.drawable.ic_account_unselected,
                "Tài khoản"
            )
        )
    }

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }
    selectedItem = when (backStackState?.destination?.route) {
        Route.HomeScreen.route -> 0
        Route.ExploreScreen.route -> 1
        Route.OrderScreen.route -> 2
        Route.AccountScreen.route -> 3
        else -> 0
    }

    //Hide the bottom navigation when the user is in the details screen
    val isBottomBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == Route.HomeScreen.route ||
                backStackState?.destination?.route == Route.ExploreScreen.route ||
                backStackState?.destination?.route == Route.OrderScreen.route ||
                backStackState?.destination?.route == Route.AccountScreen.route
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (isBottomBarVisible) {
                BottomNavigator(
                    items = bottomNavigationItem,
                    selected = selectedItem,
                    onItemClick = { index ->
                        when (index) {
                            0 -> navigateToTab(
                                navController = navController,
                                route = Route.HomeScreen.route
                            )

                            1 -> navigateToTab(
                                navController = navController,
                                route = Route.ExploreScreen.route
                            )

                            2 -> navigateToTab(
                                navController = navController,
                                route = Route.OrderScreen.route
                            )

                            3 -> navigateToTab(
                                navController = navController,
                                route = Route.AccountScreen.route
                            )
                        }
                    }
                )
            }
        }
    ) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(
                route = Route.HomeScreen.route
            ) {
                HomeScreen(navController = navController)
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
                route = Route.ExploreScreen.route
            ) {
                val currentDate = getCurrentDate()
                ExploreScreen(
                    navigateToDetail = {hotel ->
                        navigateToDetails(
                            navController = navController,
                            hotel = hotel,
                            checkInDate = dateToString(currentDate),
                            checkOutDate = dateToString(currentDate),
                            adult = "1",
                            children = "1"
                        )
                    }
                )
            }
            composable(
                route = Route.OrderScreen.route
            ) {
                OrderScreen()
            }
            composable(
                route = Route.IntroScreen.route
            ) {
                IntroScreen(navController)
            }
            composable(
                route = Route.AccountScreen.route
            ) {
                AccountScreen(navController = navController)
            }
            composable(
                route = Route.ChangeInformationScreen.route
            ) {
                ChangeInformationScreen(navController = navController)
            }
            composable(
                route = Route.ContactScreen.route
            ) {
                ContactScreen(navController = navController)
            }
            composable(
                route = Route.QRCodeScanner.route
            ) {
                QRCodeScanner(navController = navController)
            }
            composable(
                route = Route.QRCodeScreen.route
            ) {
                QRCodeScreen()
            }
            composable(
                route = Route.ChangePasswordScreen.route
            ) {
                ChangePasswordScreen(navController = navController)
            }
            composable(
                route = Route.NavigatorScreen.route
            ) {
                NavigatorScreen()
            }
            composable(
                route = Route.SearchScreen.route,
                arguments = listOf(
                    navArgument("searchQuery") {
                        type = NavType.StringType
                    },
                    navArgument("checkInDate") {
                        type = NavType.StringType
                    },
                    navArgument("checkOutDate") {
                        type = NavType.StringType
                    },
                    navArgument("adult") {
                        type = NavType.StringType
                    },
                    navArgument("children") {
                        type = NavType.StringType
                    }
                )
            ) { backStackEntry ->
                val searchQuery = backStackEntry.arguments?.getString("searchQuery").toString()
                val checkInDate = backStackEntry.arguments?.getString("checkInDate").toString()
                val checkOutDate = backStackEntry.arguments?.getString("checkOutDate").toString()
                val adult = backStackEntry.arguments?.getString("adult").toString()
                val children = backStackEntry.arguments?.getString("children").toString()
                val viewModel: SearchViewModel = hiltViewModel()
                SearchScreen(
                    navController = navController,
                    searchQuery = searchQuery,
                    checkInDate = checkInDate,
                    checkOutDate = checkOutDate,
                    adult = adult,
                    children = children,
                    event = viewModel::onEvent,
                    navigateToDetail = { property ->
                        navigateToDetails(
                            navController = navController,
                            hotel = property,
                            checkInDate = checkInDate,
                            checkOutDate = checkOutDate,
                            adult = adult,
                            children = children
                        )
                    }
                )
            }
            composable(
                route = Route.DetailScreen.route
            ) {
                val checkInDate = backStackState?.arguments?.getString("checkInDate").toString()
                val checkOutDate = backStackState?.arguments?.getString("checkOutDate").toString()
                val adult = backStackState?.arguments?.getString("adult").toString()
                val children = backStackState?.arguments?.getString("children").toString()
                navController.previousBackStackEntry?.savedStateHandle?.get<Hotel?>(Constant.PROPERTY)
                    ?.let {
                        property ->
                        DetailScreen(
                            hotel = property,
                            navController = navController,
                            checkInDate = checkInDate,
                            checkOutDate = checkOutDate,
                            adult = adult,
                            children = children
                        )
                    }
            }
            composable(
                route = Route.ConfirmOrderScreen.route
            ) {
                val checkInDate = backStackState?.arguments?.getString("checkInDate").toString()
                val checkOutDate = backStackState?.arguments?.getString("checkOutDate").toString()
                val numberNight = backStackState?.arguments?.getString("numberNight")?.toInt()
                val price = backStackState?.arguments?.getString("price")?.toDouble()
                val numberPeople = backStackState?.arguments?.getString("numberPeople")?.toInt()
                val hotelId = backStackState?.arguments?.getString("hotelId")?.toLong()
                ConfirmOrderScreen(
                    navController = navController,
                    checkInDate = checkInDate,
                    checkOutDate = checkOutDate,
                    numberNight = numberNight!!,
                    price = price!!,
                    numberPeople = numberPeople!!,
                    hotelId = hotelId!!
                )
            }
        }
    }
}

fun navigateToTab(
    navController: NavController,
    route: String
) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}

private fun navigateToDetails(
    navController: NavController,
    hotel: Hotel,
    checkInDate: String,
    checkOutDate: String,
    adult: String,
    children: String
) {
    navController.currentBackStackEntry?.savedStateHandle?.set(Constant.PROPERTY, hotel)
    navController.navigate(
        route = Route.DetailScreen.passData(
            checkInDate = checkInDate,
            checkOutDate = checkOutDate,
            adult = adult,
            children = children
        )
    )
}
