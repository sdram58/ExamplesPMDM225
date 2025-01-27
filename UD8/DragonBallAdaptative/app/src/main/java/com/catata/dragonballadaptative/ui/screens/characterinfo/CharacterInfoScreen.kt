package com.catata.dragonballadaptative.ui.screens.characterinfo

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.catata.dragonballadaptative.navigation.Routes
import com.catata.dragonballadaptative.ui.screens.layout.AppScaffold

// Componente principal de la vista en vertical: información de un personaje
@Composable
fun CharacterInfoScreen(
    navController: NavController,
    characterIdSelected: Int,
    characterIdSelectedReset: () -> Unit = {},
    viewAuthor: Boolean,
    onAuthorInfoClick: () -> Unit,
    onFABClick: () -> Unit,
) {
    // Función para unificar las acciones al pulsar la flecha atrás tanto de la TopAppBarr como del dispositivo
    fun BackButtons() {
        characterIdSelectedReset()
        onAuthorInfoClick()
        navController.popBackStack()
        // Se intenta navegar atrás a la lista de personajes
        // Si no se consigue es que se empezó la APP desde Maestro-detalle así que
        //  se navega directamente a la lista de personajes
        if (!navController.popBackStack(Routes.CharacterList, true)) {
            navController.navigate(Routes.CharacterList)
        }
    }

    // Se captura el botón Atrás del dispositivo
    BackHandler {
        BackButtons()
    }

    AppScaffold(
        showBackArrow = true,
        onClickBlackArrow = {
            BackButtons()
        },
        showAuthorInfo = viewAuthor,
        onAuthorInfoClick = {
            onAuthorInfoClick()
        },
        onFABClick = {
            onFABClick()
        }
    ) {
        CharacterInfoItemPortrait(characterId = characterIdSelected)
    }
}


