package com.example.bookapnah.feature_book.data.repository

import android.util.Log
import com.example.bookapnah.feature_book.data.data_source.BooksDao
import com.example.bookapnah.feature_book.data.remote.AddCartResDto
import com.example.bookapnah.feature_book.data.remote.BooksApi
import com.example.bookapnah.feature_book.data.remote.dto.books.BookDetailResDto
import com.example.bookapnah.feature_book.data.remote.dto.books.BookDto
import com.example.bookapnah.feature_book.data.remote.dto.cart.CartBooks
import com.example.bookapnah.feature_book.domain.model.BookDetails
import com.example.bookapnah.feature_book.domain.model.CartItem
import com.example.bookapnah.feature_book.domain.repository.BooksRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val api: BooksApi,
    private val booksDao: BooksDao
) : BooksRepository {
    override suspend fun getBooks(
        pageNo: Int,
        newBook: Boolean,
        keyword: String?,
        recent: Boolean,
        topSelling: Boolean
    ): List<BookDto> {
        return api.getAllBooks(pageNo,newBook,keyword,recent,topSelling).books
    }

    override suspend fun getBook(id: String) : BookDetailResDto{
        return api.getBookDetails(id)
    }

    override suspend fun addToCart(cartItem: CartItem,token:String): AddCartResDto {
       return  api.addToCart(cartItem,token)
     }

    override suspend fun removeFromCart(name:String, token: String) {
        return api.removeFromCart(name,token)
    }

    override suspend fun getAllCart(token: String): CartBooks {
        return  api.getAllCartItems(token)
    }

    override fun getLocalBooks(): Flow<List<BookDetails>> {
        return  booksDao.getAllBooks()
    }

    override suspend fun insertBook(bookDetails: BookDetails) {
        booksDao.insertBook(bookDetails)
    }

    override suspend fun deleteBook(bookDetails: BookDetails) {
        booksDao.deleteBook(bookDetails)
    }
}