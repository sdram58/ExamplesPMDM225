package com.catata.bookviewmodelflows

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.catata.bookviewmodelflows.navigation.Navigation
import com.catata.bookviewmodelflows.ui.theme.BookViewModelFlowsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookViewModelFlowsTheme {
                Navigation()
            }
        }
    }
}