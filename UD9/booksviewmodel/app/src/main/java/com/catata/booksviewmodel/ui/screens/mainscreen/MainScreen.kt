package com.catata.booksviewmodel.ui.screens.mainscreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.catata.booksviewmodel.model.Book
import com.catata.booksviewmodel.ui.screens.layout.AppScaffold
import com.catata.booksviewmodel.viewmodel.BookViewModel

@Composable
fun MainScreen(
    onBookClick: (Book) -> Unit = {},
    bookViewModel: BookViewModel
) {
    AppScaffold(showBackArrow = false) {
        // Suscripción a la lista de libros del ViewModel
        val books: List<Book> by bookViewModel.books.observeAsState(initial = emptyList())
        // Suscripción a la variable que indica si se están consiguiendo la lista de libros
        val isLoadingBooks: Boolean by bookViewModel.isLoading.observeAsState(initial = false)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
        ) {
            HorizontalDivider(
                thickness = 2.dp,
                color = MaterialTheme.colorScheme.onPrimary,
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(vertical = 8.dp)
                    .weight(7.7f)
            ) {
                items(books) { book ->
                    if (book.visible) {
                        BookCard(
                            book = book,
                            onBookClick = {
                                // Al clicar sobre el libro se actualiza en el ViewModel el libro seleccionado
                                bookViewModel.onBookClicked(book)
                                // Se ejecuta el código recibido en la lamnda: navegar a la ventana de información
                                onBookClick(it)
                            },
                            onBookDelete = {
                                // Al clicar sobre el icono de papelera se elimina el libro de la lista del ViewModel
                                bookViewModel.deleteBook(book)
                            }
                        )
                    }
                }
            }

            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().weight(.3f)
            ) {
                Text(
                    text = "Recarga la lista de libros",
                    color = MaterialTheme.colorScheme.onPrimary
                )
                IconButton(onClick = {
                    bookViewModel.loadBookList()
                }) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Recargar",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }

            // Cuando la variable del ViewModel es true es que se está cargando la lista de libros
            //  en ese caso se mostrará un texto y una línea de progreso indicando la carga
            if (isLoadingBooks) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.primary)
                ) {
                    Text(
                        text = "Loading...",
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    }
}