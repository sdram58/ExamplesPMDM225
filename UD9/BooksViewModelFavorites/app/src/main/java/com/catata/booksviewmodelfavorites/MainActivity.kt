package com.catata.booksviewmodelfavorites

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.catata.booksviewmodelfavorites.navigation.Navigation
import com.catata.booksviewmodelfavorites.ui.theme.BooksViewModelFavoritesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BooksViewModelFavoritesTheme {
                Navigation()
            }
        }
    }
}