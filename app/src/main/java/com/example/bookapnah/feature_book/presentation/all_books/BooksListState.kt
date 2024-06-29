package com.example.bookapnah.feature_book.presentation.all_books

import com.example.bookapnah.feature_book.domain.model.Book

/**
 * ALL THE DETAILS OF BOOK ARE STORED IN THIS FORMAT
 **/
data class BooksListState(
    val books: List<Book> = emptyList(),
    var category: List<Category> = listOf( Category.DEFAULT),
    val search: String? = null,
    val isLoading: Boolean = false,
    val isCategoryVisible: Boolean = false,
    val error: String = "",
    val pageNo: Int = 1
)