package com.catata.dragonballadaptative.ui.screens.masterdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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

// Componente para mostrar la información de personaje en Maestro-detalle
@Composable
fun CharacterInfoItemMasterDetail(
    modifier: Modifier,
    characterId: Int,
    scrollStateInfoCharacter: LazyListState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .then(modifier)
    ) {
        // Se obtienen los datos del personaje seleccionado
        val character = Character.getCharacterById(characterId)

        Row() {
            Column(
                modifier = Modifier.weight(6f)
            ) {
                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
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
                }
                HorizontalDivider(
                    color = MaterialTheme.colorScheme.secondary,
                    thickness = 2.dp,
                    modifier = Modifier.padding(top = 4.dp, bottom = 8.dp)
                )
                // Para mostrar el resto de información se utiliza un LazyColumn cuyo state (scroll)
                //  se recibe desde fuera ya que desde "fuera" se reseteará el scroll cuando se cambie de personaje
                LazyColumn(
                    state = scrollStateInfoCharacter,
                ) {
                    item {
                        CharacterDataItem(
                            title = "Año de nacimiento",
                            info = if (character.birthdayYear != 0) character.birthdayYear.toString() else "desconocido"
                        )
                        CharacterDataItem(
                            title = "Género",
                            info = character.gender
                        )
                        CharacterDataItem(
                            title = "Otros nombres",
                            info = character.otherName.ifEmpty { "desconocidos" }
                        )
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
                            textAlign = TextAlign.Justify,
                            modifier = Modifier.padding(bottom = 45.dp)
                        )
                    }
                }
            }
            // Se carga la imagen con la librería Coil
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(character.photo)
                    .crossfade(true)
                    .build(),
                contentDescription = "Foto de ${character.spanishName}",
                modifier = Modifier
                    .weight(4f)
                    .fillMaxHeight()
                    .padding(vertical = 4.dp),
                error = painterResource(R.drawable.dragonball),
            )
        }
    }
}

