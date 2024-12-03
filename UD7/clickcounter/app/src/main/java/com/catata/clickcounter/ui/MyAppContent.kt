package com.catata.clickcounter.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.catata.clickcounter.ui.theme.ClickCounterTheme

@Composable
fun MyAppContent(content: @Composable (innerPadding:PaddingValues) -> Unit) {
    ClickCounterTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            content(innerPadding)
        }
    }
}
