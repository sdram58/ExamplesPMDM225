package com.catata.dragonballadaptative.ui.screens.characterlist

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.catata.dragonballadaptative.navigation.Routes
import com.catata.dragonballadaptative.ui.screens.common.CharactersNamesLazyList
import com.catata.dragonballadaptative.ui.screens.layout.AppScaffold

// Componente principal de la vista en vertical: lista de personajes
@Composable
fun CharacterListScreen(
    navController: NavController,
    characterIdSelected: Int,
    onCharacterClick: (Int) -> Unit,
    viewAuthorInfo: Boolean,
    onAutorInfoClick: () -> Unit,
    onFABClick: () -> Unit,
) {
    AppScaffold(
        showBackArrow = false,
        showAuthorInfo = viewAuthorInfo,
        onAuthorInfoClick = {
            onAutorInfoClick()
        },
        onFABClick = {
            onFABClick()
        }
    ) {
        CharactersNamesLazyList(
            onCharacterClick = {
                onCharacterClick(it)
                navController.navigate(Routes.CharacterInfo(it))
            },
            characterId = characterIdSelected,
            selectCharacter = false
        )
    }
}
