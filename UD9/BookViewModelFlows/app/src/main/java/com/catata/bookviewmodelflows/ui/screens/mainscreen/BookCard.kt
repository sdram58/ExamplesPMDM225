package com.catata.bookviewmodelflows.ui.screens.mainscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.catata.bookviewmodelflows.model.Book
import com.catata.bookviewmodelflows.viewmodel.BookViewModel

@Composable
fun BookCard(
    book: Book,
    onBookClick: (Book) -> Unit,
    onBookDelete: (Book) -> Unit,
) {
    OutlinedCard(
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .clickable {
                onBookClick(book)
            }
    ) {
        ListItem(
            headlineContent = { Text(text = book.title) },
            supportingContent = { Text(text = book.author) },
            leadingContent = {
                if (book.favorite) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "book",
                        tint = Color(0xFFFB8C00)
                    )
                }
            },
            trailingContent = {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "book",
                    modifier = Modifier.clickable {
                        onBookDelete(book)
                    }
                )
            }
        )
    }
}


