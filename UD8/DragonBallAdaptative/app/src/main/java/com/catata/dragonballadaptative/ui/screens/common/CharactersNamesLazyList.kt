package com.catata.dragonballadaptative.ui.screens.common

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.catata.dragonballadaptative.model.Character
import kotlinx.coroutines.launch

// Componente para mostrar la lista de nombres de personajes con Sticky Header
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CharactersNamesLazyList(
    modifier: Modifier = Modifier,
    characterId: Int = 0,               // State Hoisting: variable de estado, si se recibe se podrá marcar el personaje con un icono de check
    onCharacterClick: (Int) -> Unit,    // State Hoisting: lambda para informar del personaje que se selecciona
    selectCharacter: Boolean = false,   // si se está en Maestro-Detalle se pasa true para que se marque el personaje si no, no tiene sentido marcarlo ya que no se verá la lista al seleccionar personaje
) {
    // Variable de estado para poder llevar el scroll de la lista de personajes al seleccionado
    val scrollStateListCharacter = rememberLazyListState()
    // Variable de estado necesaria para poder mover el scroll en segundo plano
    val courutineScope = rememberCoroutineScope()

    // Se usa un LazyColumn para que se gestione automáticamente el scroll
    LazyColumn(
        modifier = modifier,
        state = scrollStateListCharacter
    ) {
        // El LazyColumn tiene StickyHeaders así que se usa la función sorted() de Character para obtener
        //  un Map con la primera letra del personaje y una lista de Personajes que empiezan por esa letra
        val groupedCharacter: Map<Char, List<Character>> = Character.sorted().groupBy { it.spanishName[0] }
        groupedCharacter.forEach { (header, characters) ->
            stickyHeader {
                CharactersLettersHeader(header)
                HorizontalDivider(
                    color = MaterialTheme.colorScheme.primary,
                    thickness = 1.dp
                )
            }

            items(characters) {
                // Cuando se añaden los personajes si coincide con el seleccionado se indica con el parámetro selected a true
                CharacterNameItem(
                    item = it,
                    onClick = {
                        onCharacterClick(it)
                    },
                    selected = selectCharacter && it.id==characterId
                )
            }
        }

        courutineScope.launch {
            if (characterId!=0) {
                // Se obtiene la posición del grupo en el que se encuentra el personaje (grupos por inicial del nombre)
                val firstCharGroupPosition = Character
                    .sorted()
                    .groupBy {
                        it.spanishName[0]
                    }
                    .map { (index, _) ->
                        index
                    }
                    .indexOf(Character.getCharacterById(characterId).spanishName.first())
                // Se obtiene la posición del personaje en la lista de personajes ordenados alfabéticamente
                val characterPosition = Character.sorted().map{it.id}.indexOf(characterId)
                // La posición final es la suma de las dos posiciones
                val finalPosition = firstCharGroupPosition+characterPosition+1
                // Se anima el scroll a la posición del personaje seleccionado
                scrollStateListCharacter.animateScrollToItem(finalPosition-1,0)
            }
        }
    }
}

// Componente propio para las letras de los Stickyheaders
@Composable
fun CharactersLettersHeader(header: Char) {
    Text(
        text = header.toString(),
        fontSize = 24.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primaryContainer)
            .fillMaxWidth()
    )
}

// Componente propio para los nombres de los personajes en la lista
//  Implementa StateHoisting para poder informar al padre cuando se hace clic en un elemento
@Composable
fun CharacterNameItem(
    item: Character,
    onClick: (Int) -> Unit,
    selected: Boolean = false
) {
    Row(
        modifier = Modifier
            .clickable {
                onClick(item.id)
            }
            .background(
                if (selected) {
                    MaterialTheme.colorScheme.tertiaryContainer
                } else {
                    MaterialTheme.colorScheme.background
                }
            )
            .fillMaxWidth()
            .padding(
                top = 8.dp,
                bottom = 8.dp,
                start = 16.dp
            )
    ) {
        if (selected) {
            Icon(
                imageVector = Icons.Default.Done,
                contentDescription = "Seleccionado"
            )
        }
        Text(
            text = item.spanishName,
            fontSize = 18.sp,
        )
    }
}