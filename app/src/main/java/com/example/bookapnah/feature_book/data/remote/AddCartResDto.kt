package com.example.bookapnah.feature_book.data.remote

import com.example.bookapnah.feature_book.data.remote.dto.books.BookDto
import com.example.bookapnah.feature_book.domain.model.Book
import com.example.bookapnah.feature_book.domain.model.CartResponse

data class AddCartResDto(
    val success: Boolean
)

fun AddCartResDto.toCartRes(): CartResponse {
    return CartResponse(
        success
    )
}