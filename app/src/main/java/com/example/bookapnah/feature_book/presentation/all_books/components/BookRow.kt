package com.example.bookapnah.feature_book.presentation.all_books.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bookapnah.feature_book.domain.model.Book

/**
 * book row
 **/
@Composable
fun BookRow(
    rowIndex: Int, entries: List<Book>, navController: NavController
) {
    Column {
        Row {
            BookEntry(
                entry = entries[rowIndex * 2],
                modifier = Modifier.weight(1f),
                navController = navController,
            )
            Spacer(modifier = Modifier.width(16.dp))
            if (entries.size >= rowIndex * 2 + 2) {
                BookEntry(
                    entry = entries[rowIndex * 2 + 1],
                    modifier = Modifier.weight(1f),
                    navController = navController
                )
            } else {
                Spacer(modifier = Modifier.weight(1f))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}
