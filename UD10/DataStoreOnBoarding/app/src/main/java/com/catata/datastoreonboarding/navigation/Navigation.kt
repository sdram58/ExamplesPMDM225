package com.catata.datastoreonboarding.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.catata.datastoreonboarding.ui.screens.SplashScreen
import com.catata.datastoreonboarding.ui.screens.mainscreen.MainScreen
import com.catata.datastoreonboarding.ui.screens.onboarding.OnBoarding1
import com.catata.datastoreonboarding.ui.screens.onboarding.OnBoarding2
import com.catata.datastoreonboarding.viewmodel.PreferencesViewModel

@Composable
fun Navigation(prefsViewModel: PreferencesViewModel) {
    //Constante para gestionar el estado y se debe propagar entre todas las pantallas
    val navController = rememberNavController()

    //Elemento que conoce las diferentes pantallas y cual es la primera en lanzarse
    NavHost(
        navController = navController,
        startDestination = Routes.SplashScreen
    ){
        //Definición de la primera pantalla
        composable<Routes.SplashScreen>{
            SplashScreen(navController, prefsViewModel)
        }

        composable<Routes.Onboarding1Screen>{
            OnBoarding1(navController, prefsViewModel)
        }

        composable<Routes.Onboarding2Screen>{
            OnBoarding2(navController, prefsViewModel)
        }

        composable<Routes.MainScreen>{
            MainScreen(prefsViewModel)
        }


    }

}