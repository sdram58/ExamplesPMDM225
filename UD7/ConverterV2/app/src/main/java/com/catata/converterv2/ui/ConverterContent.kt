package com.catata.converterv2.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.catata.converterv2.ui.theme.ConverterV2Theme

@Composable
fun ConverterContent(content: @Composable (modifier: Modifier) -> Unit) {
    ConverterV2Theme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            content(Modifier.padding(innerPadding))
        }
    }
}

