package com.example.bookapnah.feature_book.domain.repository

import com.example.bookapnah.feature_book.data.remote.AddCartResDto
import com.example.bookapnah.feature_book.data.remote.dto.books.BookDetailResDto
import com.example.bookapnah.feature_book.data.remote.dto.books.BookDto
import com.example.bookapnah.feature_book.data.remote.dto.cart.CartBooks
import com.example.bookapnah.feature_book.domain.model.BookDetails
import com.example.bookapnah.feature_book.domain.model.CartItem
import kotlinx.coroutines.flow.Flow


/**
 * THIS IS A FAKE REPOSITORY AND THE REAL IMPLEMENTATION OF THIS
 * WILL BE USED
 *
 * IT CAN BE USED FOR TESTING
 * IT CAN BE USED FOR REQUESTING API
 **/
interface BooksRepository {

    suspend fun getBooks(
        pageNo: Int = 1,
        newBook: Boolean = false,
        keyword: String? = null,
        recent: Boolean = false,
        topSelling: Boolean = false
    ): List<BookDto>


    suspend fun getBook(
        id:String
    ):BookDetailResDto

    suspend fun  addToCart(
        cartItem: CartItem,
        token:String
    ):AddCartResDto

    suspend fun  removeFromCart(
        name: String,
        token:String
    ):Unit

    suspend fun  getAllCart(
        token:String
    ):CartBooks

    fun getLocalBooks() : Flow<List<BookDetails>>


    suspend fun insertBook(bookDetails: BookDetails)

   suspend fun deleteBook(bookDetails: BookDetails)
}