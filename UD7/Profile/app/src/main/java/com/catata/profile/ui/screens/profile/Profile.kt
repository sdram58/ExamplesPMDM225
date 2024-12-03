package com.catata.profile.ui.screens.profile

import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.PersonRemove
import androidx.compose.material.icons.filled.PlusOne
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.twotone.Flight
import androidx.compose.material.icons.twotone.Movie
import androidx.compose.material.icons.twotone.SportsEsports
import androidx.compose.material.icons.twotone.SportsMotorsports
import androidx.compose.material.icons.twotone.Wallpaper
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.catata.profile.R
import com.catata.profile.ui.ProfileContent

@Composable
fun Profile(modifier: Modifier) {
    val hobbies = mapOf(
        Icons.TwoTone.SportsMotorsports to "Motos",
        Icons.TwoTone.Wallpaper to "Manga y Anime",
        Icons.TwoTone.SportsEsports to "Videojuegos",
        Icons.TwoTone.Movie to "Películas de Súper Heroes",
        Icons.TwoTone.Flight to "Viajar",
    )

    var messages by rememberSaveable { mutableStateOf(0) }
    var follow by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppHeader()

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 60.dp)
        ) {
            MyFollowButton(follow){
                follow = !it
            }


            BadgedBox(badge = {
                Badge() {
                    Text(text = "${messages}")
                }
            }) {
                Icon(
                    imageVector = Icons.Filled.MailOutline,
                    contentDescription = "Mensajes",
                    modifier = Modifier.size(30.dp)
                )
            }
        }

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 5.dp),
            thickness = DividerDefaults.Thickness,
            color = colorResource(id = R.color.orange)
        )

        Hobbies(hobbies)

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 5.dp),
            thickness = DividerDefaults.Thickness,
            color = colorResource(id = R.color.orange)
        )
        Row {
            ImageLike(
                image = R.drawable.rick1,
                description = "Con mi nieto"
            )
            Spacer(modifier = Modifier.width(30.dp))
            ImageLike(
                image = R.drawable.rick2,
                description = "Rickinillo"
            )
        }

        Row(
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.height(100.dp)
        ) {
            Button(onClick = { messages++ }) {
                Icon(
                    imageVector = Icons.Filled.PlusOne,
                    contentDescription = "+1"
                )
            }
        }

    }

}


// Componente para la imagen con el icono para "Me gusta"
@Composable
fun ImageLike(
    image: Int,
    description: String) {

    var like by rememberSaveable { mutableStateOf(false) }

    Box(
        contentAlignment = Alignment.BottomEnd
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = description,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(150.dp)
        )
        Icon(imageVector = Icons.Outlined.Favorite,
             contentDescription = "Like",
             tint = if (like) Color.Red else LocalContentColor.current,
             modifier = Modifier
                 .clickable { like = !like }
                 .size(45.dp))
    }
}

// Componente con los hobbies del usuario
@Composable
fun Hobbies(hobbies: Map<ImageVector, String>) {
    Column {
        Text(
            text = "Hobbies",
            fontSize = 25.sp
        )

        hobbies.forEach { (icon, hobby) ->
            Hobby(
                icon = icon,
                text = hobby
            )
        }
    }
}

// Componente para un hobby con la imagen y el texto
@Composable
fun Hobby(
    icon: ImageVector,
    text: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "Icono $text",
            tint = colorResource(id = R.color.orange),
            modifier = Modifier.size(30.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = text)
    }
}

@Composable
fun MyFollowButton(follow: Boolean, onClick:(Boolean)->Unit) {
    if(follow){
        OutlinedButton(
            onClick = { onClick(follow) }
        ) {
            Icon(imageVector = Icons.Default.PersonRemove, contentDescription = "Unfollow" )
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "Unfollow")
        }
    }else{
       FilledTonalButton(
           onClick = { onClick(follow)}
       ) {
           Icon(imageVector = Icons.Default.PersonAdd, contentDescription = "Follow" )
           Spacer(modifier = Modifier.width(10.dp))
           Text(text = "Follow")
       }
    }


}


// Cabecera con la imagen del usuario y su nombre
@Composable
fun AppHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(
            20.dp,
            Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically,

        ) {
        Image(
            painter = painterResource(id = R.drawable.rick),
            contentDescription = "Foto de Rick",
            modifier = Modifier
                .clip(CircleShape)
                .border(
                    width = 3.dp,
                    color = colorResource(id = R.color.orange),
                    shape = CircleShape
                )
                .width(80.dp)
        )
        Text(
            text = "Rick Sanchez",
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(
    name = "Light Mode",
    showBackground = true,
    showSystemUi = true,
)
@Preview(
    name = "Dark Mode",
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun ContentPreview() {
    ProfileContent {
        Profile(it)
    }
}