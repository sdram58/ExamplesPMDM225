package com.catata.navigationtests.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.catata.navigationtests.ui.screens.FirstScreen
import com.catata.navigationtests.ui.screens.SecondScreen

@Composable
fun Navigation() {
    // Constant to manage the state, must be propagated across all screens
    val navController = rememberNavController()

    // Element that knows the different screens and which is the first to launch
    NavHost(
        navController = navController,
        startDestination = Routes.First // Route where the application starts
    ) {

        // Definition of the first screen
        composable<Routes.First> {
            FirstScreen(navController)
        }

        // Definition of the second screen
        composable<Routes.Second> {
            // Parameters are obtained from the route to use them when loading the screen
            val args = it.toRoute<Routes.Second>()
            SecondScreen(navController, args.name, args.age)
        }


//      // Same functionality but using Screens that receive lambda functions for navigation
//      // that way all navigation (or most of it) is managed from here.
//      // Definition of the first screen
//        composable<First> {
//            FirstScreenLambdaNavigation(navController) { name, age ->
//                navController.navigate(
//                    Second(
//                        name = name,
//                        age = age.toIntOrNull() ?: 0
//                    )
//                )
//            }
//        }
//        // Definición de la segunda pantalla
//        composable<Second> {
//            // Se obtienen los parámetros de la ruta para poder usarlos al cargar la pantalla
//            val args = it.toRoute<Second>()
//            SecondScreenLambdaNavigation(navController, args.name, args.age) {
////                navController.popBackStack()
//                navController.navigate(First) {
//                    popUpTo<First> {
//                        inclusive = true
//                    }
//                }
////                navController.navigateUp()
//            }
//        }
    }
}


