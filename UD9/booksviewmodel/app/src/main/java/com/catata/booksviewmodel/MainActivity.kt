package com.catata.booksviewmodel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.catata.booksviewmodel.navigation.Navigation
import com.catata.booksviewmodel.ui.theme.BooksViewModelTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BooksViewModelTheme {
                Navigation()
            }
        }
    }
}