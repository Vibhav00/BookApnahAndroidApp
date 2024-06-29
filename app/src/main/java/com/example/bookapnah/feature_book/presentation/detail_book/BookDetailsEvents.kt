package com.example.bookapnah.feature_book.presentation.detail_book

sealed class BookDetailsEvents {
    data object AddToCart:BookDetailsEvents()
    data object AddToLocalDatabase:BookDetailsEvents()

}