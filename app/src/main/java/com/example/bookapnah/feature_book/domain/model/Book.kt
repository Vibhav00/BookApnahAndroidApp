package com.example.bookapnah.feature_book.domain.model

data class Book(
    val id: String,
    val author: String,
    val book_depository_stars: Double,
    val category: String,
    val image: String,
    val name: String,
    val price: Int,
)
