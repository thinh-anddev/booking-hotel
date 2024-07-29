package com.example.booking_hotel.presentation.navigator

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.booking_hotel.R
import com.example.booking_hotel.presentation.account.AccountScreen
import com.example.booking_hotel.presentation.explore.ExploreScreen
import com.example.booking_hotel.presentation.home.HomeScreen
import com.example.booking_hotel.presentation.navgraph.Route
import com.example.booking_hotel.presentation.navigator.components.BottomNavigator
import com.example.booking_hotel.presentation.navigator.components.BottomNavigatorItem
import com.example.booking_hotel.presentation.ordered.OrderScreen

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
        mutableStateOf(0)
    }
    selectedItem = when (backStackState?.destination?.route) {
        Route.HomeScreen.route -> 0
        Route.ExploreScreen.route -> 1
        Route.OrderScreen.route -> 2
        Route.AccountScreen.route -> 3
        else -> 0
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
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
                HomeScreen()
            }
            composable(
                route = Route.ExploreScreen.route
            ) {
                ExploreScreen()
            }
            composable(
                route = Route.OrderScreen.route
            ) {
                OrderScreen()
            }
            composable(
                route = Route.AccountScreen.route
            ) {
                AccountScreen()
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