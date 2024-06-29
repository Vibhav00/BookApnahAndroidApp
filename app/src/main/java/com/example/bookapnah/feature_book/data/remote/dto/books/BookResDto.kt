package com.example.bookapnah.feature_book.data.remote.dto.books

data class BookResDto(
    val books: List<BookDto>,
    val filteredBooksCount: Int,
    val productsCount: Int,
    val resultPerPage: Int,
    val success: Boolean
)