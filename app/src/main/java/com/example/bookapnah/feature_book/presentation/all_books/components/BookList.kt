package com.example.bookapnah.feature_book.presentation.all_books.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bookapnah.feature_book.presentation.all_books.AllBooksViewModel
import com.example.bookapnah.feature_book.presentation.all_books.BooksEvent

/**
 * contains all books
 **/
@Composable
fun BookList(
    viewModel: AllBooksViewModel = hiltViewModel(), navController: NavController
) {

    val pokemonList = viewModel.stateBook.value.books
    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        val itemCount = if (pokemonList.size % 2 == 0) {
            pokemonList.size / 2
        } else {
            pokemonList.size / 2 + 1
        }
        items(itemCount) {
            if (it >= itemCount - 1) {
                LaunchedEffect(key1 = true) {
                    viewModel.onEvent(BooksEvent.Paginate(viewModel.stateBook.value.pageNo + 1))
                }
            }
            BookRow(rowIndex = it, entries = pokemonList, navController = navController)
        }
    }

}