package com.catata.bookviewmodelflows.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.catata.bookviewmodelflows.viewmodel.BookViewModel
import com.catata.bookviewmodelflows.ui.screens.bookinfoscreen.BookInfoScreen
import com.catata.bookviewmodelflows.ui.screens.mainscreen.MainScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    val bookViewModel = remember { BookViewModel() }

    NavHost(
        navController = navController,
        startDestination = Routes.Main, // Ruta por la que comenzará la aplicación,
        enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
        exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) },
        popEnterTransition = { slideInHorizontally(initialOffsetX = { -it }) },
        popExitTransition = { slideOutHorizontally(targetOffsetX = { it }) },
    ) {
        composable<Routes.Main> {
            MainScreen(
                onBookClick = {
                    navController.navigate(Routes.BookInfo)
                },
                bookViewModel = bookViewModel
            )
        }

        composable<Routes.BookInfo> {
            BookInfoScreen(
                onBackArrowClick = {
                    navController.popBackStack()
                },
                bookViewModel = bookViewModel
            )
        }
    }
}

