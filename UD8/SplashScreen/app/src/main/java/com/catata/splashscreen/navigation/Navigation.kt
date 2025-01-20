package com.catata.splashscreen.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.catata.splashscreen.ui.screens.MainScreen
import com.catata.splashscreen.ui.screens.SplashScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Splash
    ) {
        composable<Splash> {
            SplashScreen(navController)
        }
        composable<Main> {
            MainScreen(navController)
        }
    }
}

