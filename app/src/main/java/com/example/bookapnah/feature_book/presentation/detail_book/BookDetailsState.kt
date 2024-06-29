package com.example.bookapnah.feature_book.presentation.detail_book

import com.example.bookapnah.feature_book.domain.model.BookDetails

data class BookDetailsState (
    val isLoading: Boolean = false,
    val book: BookDetails? = null,
    val error: String = "",
)