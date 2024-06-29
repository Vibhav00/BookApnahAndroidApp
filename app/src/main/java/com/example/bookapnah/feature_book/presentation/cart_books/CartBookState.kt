package com.example.bookapnah.feature_book.presentation.cart_books

import com.example.bookapnah.feature_book.data.remote.dto.cart.CartItemRes

data class CartBookState(
    val books: List<CartItemRes> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""

)
