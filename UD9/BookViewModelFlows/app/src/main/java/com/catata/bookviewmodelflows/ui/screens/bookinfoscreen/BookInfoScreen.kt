package com.catata.bookviewmodelflows.ui.screens.bookinfoscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.catata.bookviewmodelflows.model.Book
import com.catata.bookviewmodelflows.ui.screens.layout.AppScaffold
import com.catata.bookviewmodelflows.viewmodel.BookViewModel

@Composable
fun BookInfoScreen(
    onBackArrowClick: () -> Unit = {},
    bookViewModel: BookViewModel
) {
    AppScaffold(
        showBackArrow = true,
        onBlackArrowClick = onBackArrowClick
    ) {
        // Suscripción al libro seleccionado del ViewModel
        val book: Book by bookViewModel.selectedBook.collectAsState()

        //var favorite by rememberSaveable { mutableStateOf(book.favorite) }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.MenuBook,
                        contentDescription = "book"
                    )
                    if (book.favorite) {
                        Spacer(modifier = Modifier.height(4.dp))
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "book",
                            tint = Color(0xFFFB8C00)
                        )
                    }
                }
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = book.title,
                    fontSize = 30.sp,
                    lineHeight = 38.sp
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = book.author,
                fontSize = 16.sp
            )

            TextButton(onClick = {
                bookViewModel.markAsFavorite()
            }) {
                Text(text = if (book.favorite) "Quitar favorito" else "Marcar favorito")
            }

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.tertiary)
                    .padding(8.dp)
            ) {
                Text(
                    text = "Aquí se mostrará la información detallada del libro.",
                    color = MaterialTheme.colorScheme.onTertiary
                )
            }
        }
    }
}