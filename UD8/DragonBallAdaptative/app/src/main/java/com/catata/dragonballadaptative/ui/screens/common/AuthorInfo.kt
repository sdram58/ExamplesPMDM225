package com.catata.dragonballadaptative.ui.screens.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.catata.dragonballadaptative.R

// Componente propio para mostrar la información del autor
@Composable
fun AuthorInfo(
    visible: Boolean,
    onClick: () -> Unit)
{
    if (visible) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = .5f))
                .fillMaxSize()
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .border(
                        width = 4.dp,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        shape = RoundedCornerShape(5.dp)
                    )
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .padding(16.dp),
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
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
        }
    }
}