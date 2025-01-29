package com.catata.booksviewmodelfavorites.ui.screens.mainscreen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.catata.booksviewmodelfavorites.model.Book
import com.catata.booksviewmodelfavorites.viewmodel.BookViewModel

@Composable
fun SearchField(
    bookViewModel: BookViewModel,
    modifier: Modifier = Modifier
) {
    var searchString by rememberSaveable { mutableStateOf("") }
    val deleteSearchEnabled by remember {
        derivedStateOf {
            searchString.isNotEmpty()
        }
    }

    Row(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        TextField(
            value = searchString,
            onValueChange = {
                if (searchString.length > it.length) {
                    bookViewModel.resetSearchList()
                }

                if (it.isNotEmpty()) {
                    bookViewModel.searchBook(it)
                }
                searchString = it
            },
            label = { Text(text = "Buscar libro") },
            trailingIcon = {
                if (deleteSearchEnabled) {
                    IconButton(onClick = {
                        searchString = ""
                        bookViewModel.resetSearchList()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Cancel,
                            contentDescription = "Cancelar búsqueda"
                        )
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}
