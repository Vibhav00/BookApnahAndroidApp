package com.example.bookapnah.feature_book.data.remote.dto.cart

import com.example.bookapnah.feature_book.domain.model.CartItem

data class CartItemRes(
    val _id: String,
    val author: String,
    val image: String,
    val name: String,
    val price: String,
    val printing: String
)

fun CartItemRes.toCartItem():CartItem{
    return  CartItem(this.name,null,null,null,null)
}