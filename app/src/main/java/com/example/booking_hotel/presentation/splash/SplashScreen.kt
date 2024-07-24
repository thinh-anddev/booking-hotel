package com.example.booking_hotel.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.booking_hotel.R
import com.example.booking_hotel.presentation.navgraph.Route
import com.example.booking_hotel.ui.theme.TextColor

@Composable
fun SplashScreen(
    viewModel: SplashViewModel = hiltViewModel(),
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val navigateToNextScreen by viewModel.navigateToNextScreen.observeAsState()

    LaunchedEffect(key1 = navigateToNextScreen) {
        if (navigateToNextScreen == true) {
            navController.navigate(Route.IntroScreen.route) {
                popUpTo(Route.SplashScreen.route) {inclusive = true}
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painterResource(id = R.drawable.logo_app), contentDescription = null)

        Text(text = "Good trip, good day!", style = TextStyle(
            color = TextColor,
            fontFamily = FontFamily(Font(R.font.lato_bold)),
            fontSize = 26.sp
        ))
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview(){
    val viewModel: SplashViewModel = hiltViewModel()
    val navController = rememberNavController()
    SplashScreen(viewModel, navController)
}