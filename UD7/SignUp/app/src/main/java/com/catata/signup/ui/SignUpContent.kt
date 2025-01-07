package com.catata.signup.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.catata.signup.ui.theme.SignUpTheme

@Composable
fun SignUpContent(content: @Composable (modifier:Modifier) -> Unit) {
    SignUpTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            content(Modifier.padding(innerPadding))
        }
    }

}