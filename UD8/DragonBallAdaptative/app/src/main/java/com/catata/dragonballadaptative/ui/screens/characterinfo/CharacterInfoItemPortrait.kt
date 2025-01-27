package com.catata.dragonballadaptative.ui.screens.characterinfo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.catata.dragonballadaptative.R
import com.catata.dragonballadaptative.model.Character
import com.catata.dragonballadaptative.ui.screens.common.CharacterDataItem

// Componente para mostrar la información de personaje en vista vertical
@Composable
fun CharacterInfoItemPortrait(
    modifier: Modifier = Modifier,
    characterId: Int,
    scrollStateInfoCharacter: LazyListState = rememberLazyListState()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .then(modifier)
    ) {
//        if (characterId!=0) {
            // Se obtienen los datos del personaje seleccionado
            val character = Character.getCharacterById(characterId)
            Column {
                Text(
                    text = character.spanishName,
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "(${character.japaneseName})",
                    fontSize = 20.sp
                )
                HorizontalDivider(
                    color = MaterialTheme.colorScheme.secondary,
                    thickness = 2.dp,
                    modifier = Modifier.padding(top = 4.dp, bottom = 8.dp)
                )
                // Para mostrar el resto de información se utiliza un LazyColumn cuyo state (scroll)
                //  se recibe desde fuera ya que desde fuera se reseteará el scroll cuando se cambie de personaje
                LazyColumn(
                    state = scrollStateInfoCharacter,
                    modifier = Modifier.padding(bottom = 60.dp)
                ) {
                    item {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(character.photo)
                                .crossfade(true)
                                .build(),
                            contentDescription = "Foto de ${character.spanishName}",
                            modifier = Modifier
                                .height(300.dp)
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            error = painterResource(R.drawable.dragonball),
                        )
                    }
                    item {
                        CharacterDataItem(
                            title = "Año de nacimiento",
                            info = if (character.birthdayYear != 0) character.birthdayYear.toString() else "desconocido"
                        )
                    }
                    item {
                        CharacterDataItem(
                            title = "Género",
                            info = character.gender
                        )
                    }
                    item {
                        CharacterDataItem(
                            title = "Otros nombres",
                            info = character.otherName.ifEmpty { "desconocidos" }
                        )
                    }
                    item {
                        CharacterDataItem(
                            title = "Especie",
                            info = character.species,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                    }
                    item {
                        Text(
                            text = "INFORMACIÓN",
                            fontSize = 30.sp,
                        )
                    }
                    item {
                        Text(
                            text = character.information,
                            textAlign = TextAlign.Justify
                        )
                    }
                }
            }
//        }
    }
}