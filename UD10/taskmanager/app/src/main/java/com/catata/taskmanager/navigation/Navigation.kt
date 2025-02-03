package com.catata.taskmanager.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.catata.taskmanager.tasks.ui.MainScreen
import com.catata.taskmanager.ui.screens.SplashScreen
import com.catata.taskmanager.tasks.ui.viewmodel.TaskViewModel


@Composable
fun Navigation(taskViewModel: TaskViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Splash
    ) {
        composable<Routes.Splash> {
            SplashScreen(navController, taskViewModel)
        }

        composable<Routes.Main> {
            MainScreen(taskViewModel)
        }
    }
}

