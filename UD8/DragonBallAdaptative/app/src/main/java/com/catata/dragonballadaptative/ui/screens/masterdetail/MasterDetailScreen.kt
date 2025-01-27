package com.catata.dragonballadaptative.ui.screens.masterdetail

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.catata.dragonballadaptative.ui.screens.common.CharactersNamesLazyList
import com.catata.dragonballadaptative.ui.screens.layout.AppScaffold
import kotlinx.coroutines.launch

// Componente principal de la vista en horizontal: Maestro-detalle
@Composable
fun MasterDetailScreen(
    navController: NavController,
    characterIdSelected: Int,
    onCharacterClick: (Int) -> Unit,
    viewAuthor: Boolean,
    onAuthorInfoClick: () -> Unit,
    onFABClick: () -> Unit,
) {
    AppScaffold(
        showAuthorInfo = viewAuthor,
        onAuthorInfoClick = {
            onAuthorInfoClick()
        },
        onFABClick = {
            onFABClick()
        }
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            // Variable de estado para poder llevar al inicio el scroll de la información del Character al seleccionar otro Character
            val scrollStateInfoCharacter = rememberLazyListState()
            // Variable de estado necesaria para poder mover el scroll en segundo plano
            val courutineScope = rememberCoroutineScope()

            CharactersNamesLazyList(
                modifier = Modifier.weight(2f),
                characterId = characterIdSelected,
                onCharacterClick = {
                    onCharacterClick(it)
                    courutineScope.launch {
                        scrollStateInfoCharacter.animateScrollToItem(0,0)
                    }
                },
                selectCharacter = true,
            )

            // Si hay personaje seleccionado se muestra su información si no se muestra un mensaje
            if (characterIdSelected!=0) {
                CharacterInfoItemMasterDetail(
                    modifier = Modifier.weight(8f),
                    characterId = characterIdSelected,
                    scrollStateInfoCharacter = scrollStateInfoCharacter
                )
            } else {
                NotCharacterSelectedCard(
                    modifier = Modifier.weight(7f)
                )
            }
        }
    }
}


