package com.catata.dragonballadaptative.ui.screens.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Componente propio para formatear algunos datos del personaje
@Composable
fun CharacterDataItem(
    modifier: Modifier = Modifier,
    title: String,
    info: String
) {
    Row(
        modifier = Modifier.fillMaxWidth().then(modifier)
    ) {
        Text(
            text = "$title:",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .weight(1f)
        )
        Text(
            text = info.replaceFirstChar { it.uppercase() },
            fontSize = 18.sp,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .weight(1f)
        )
    }
}