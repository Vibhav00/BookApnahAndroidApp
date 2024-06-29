package com.example.bookapnah.feature_book.data.remote.dto.cart

data class CartBooks(
    val cartItems: List<CartItemRes>,
    val success: Boolean
)