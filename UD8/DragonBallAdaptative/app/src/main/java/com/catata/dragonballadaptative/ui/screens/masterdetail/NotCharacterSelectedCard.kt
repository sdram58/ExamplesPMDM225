package com.catata.dragonballadaptative.ui.screens.masterdetail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WarningAmber
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Componente que muestra en Maestro-detalle un mensaje indicando que no hay personaje seleccionado
@Composable
fun NotCharacterSelectedCard(modifier: Modifier = Modifier) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        shape = CutCornerShape(16.dp),
        border = BorderStroke(width = 4.dp, brush = Brush.linearGradient(listOf(Color(0xFFFDD835), Color(0xFFFB8C00), Color(0xFFE53935)))),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .then(modifier)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
//            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                imageVector = Icons.Default.WarningAmber,
                contentDescription = "Advertencia",
                tint = Color(0xFFFB8C00),
                modifier = Modifier.size(48.dp)
            )
            Text(
                text = "Selecciona un personaje de la lista de la izquierda",
                fontSize = 44.sp,
                lineHeight = 50.sp,
                textAlign = TextAlign.Center
            )
            Icon(
                imageVector = Icons.Default.WarningAmber,
                contentDescription = "Advertencia",
                tint = Color(0xFFFB8C00),
                modifier = Modifier.size(48.dp)
            )
        }
    }
}