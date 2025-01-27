package com.catata.dragonballadaptative.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.catata.dragonballadaptative.ui.screens.characterinfo.CharacterInfoScreen
import com.catata.dragonballadaptative.ui.screens.characterlist.CharacterListScreen
import com.catata.dragonballadaptative.ui.screens.masterdetail.MasterDetailScreen

@Composable
fun Navigation() {
    // Constante para gestionar el estado, se debe propagar entre todas las pantallas
    val navController = rememberNavController()

    // Variable de estado para indicar si se debe mostrar la información del autor o no
    var viewAuthorState by rememberSaveable { mutableStateOf(false) }

    // Variable de estado para saber qué personaje se ha seleccionado
    var characterSelectedIdState by rememberSaveable { mutableIntStateOf(0) }

    // Se detecta si es una tablet para mostrar Maestro-Detalle
//    val orientationLandscape = currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass != WindowWidthSizeClass.COMPACT &&
//            currentWindowAdaptiveInfo().windowSizeClass.windowHeightSizeClass != WindowHeightSizeClass.COMPACT

    // Se navega a una ventana u otra según la orientación y el tamaño de la pantalla y si hay personaje seleccionado
    val orientationLandscape = (currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass != WindowWidthSizeClass.COMPACT &&
            currentWindowAdaptiveInfo().windowSizeClass.windowHeightSizeClass != WindowHeightSizeClass.COMPACT) ||
            currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.EXPANDED
    LaunchedEffect(orientationLandscape) {
        if (orientationLandscape) { // Navega a Maestro-detalle
            navController.popBackStack()
            navController.navigate(Routes.MasterDetail) {
                popUpTo<Routes.MasterDetail> {
                    inclusive = true
                }
            }
        } else if (characterSelectedIdState == 0) { // Navega a la lista de personajes
            navController.navigate(Routes.CharacterList) {
                popUpTo<Routes.CharacterList> {
                    inclusive = true
                }
            }
        } else {    // Navega a la información de personajes
            navController.popBackStack()
            navController.navigate(Routes.CharacterInfo(characterSelectedIdState))
        }
    }

    // Elemento que conoce las diferentes pantallas y cual es la primera en lanzarse
    NavHost(
        navController = navController,
        startDestination = if(orientationLandscape) Routes.MasterDetail else Routes.CharacterList, // Ruta por la que comenzará la aplicación,
        enterTransition = { slideInHorizontally(initialOffsetX = { it }) },
        exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) },
        popEnterTransition = { slideInHorizontally(initialOffsetX = { -it }) },
        popExitTransition = { slideOutHorizontally(targetOffsetX = { it }) },
    ) {

        composable<Routes.CharacterList> {
            CharacterListScreen(
                navController = navController,
                characterIdSelected = characterSelectedIdState,
                onCharacterClick = { characterSelectedIdState = it },
                viewAuthorInfo = viewAuthorState,
                onAutorInfoClick = { viewAuthorState = false  },
                onFABClick = { viewAuthorState = true },
            )
        }

        composable<Routes.CharacterInfo> {
            // Se obtiene el id del personaje de la ruta
            val characterIdSelected = it.toRoute<Routes.CharacterInfo>().characterId
            CharacterInfoScreen(
                navController = navController,
                characterIdSelected = characterIdSelected,
                characterIdSelectedReset = { characterSelectedIdState = 0 },
                viewAuthor = viewAuthorState,
                onAuthorInfoClick = { viewAuthorState = false  },
                onFABClick = { viewAuthorState = true },
            )
        }

        composable<Routes.MasterDetail> {
            MasterDetailScreen(
                navController = navController,
                characterIdSelected = characterSelectedIdState,
                onCharacterClick = { characterSelectedIdState = it },
                viewAuthor = viewAuthorState,
                onAuthorInfoClick = { viewAuthorState = false  },
                onFABClick = { viewAuthorState = true },
            )
        }
    }
}