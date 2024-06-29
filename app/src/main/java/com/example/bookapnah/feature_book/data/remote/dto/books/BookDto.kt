package com.example.bookapnah.feature_book.data.remote.dto.books

import com.example.bookapnah.feature_book.domain.model.Book
import com.example.bookapnah.feature_book.domain.model.BookDetails

data class BookDto(
    val __v: Int,
    val _id: String,
    val author: String,
    val book_depository_stars: Double,
    val category: String,
    val createdAt: String,
    val discount: Int,
    val format: String,
    val image: String,
    val isbn: Long,
    val name: String,
    val new_book: Boolean,
    val numOfReviews: Int,
    val old_price: Int,
    val on_sale: Boolean,
    val price: Int,
    val ratings: Int,
    val recent_comming: Boolean,
    val reviews: List<Any>,
    val stock: Int,
    val top_sellings: Boolean
)



/**
 * FUNCTION TO CONVERT  BOOK DTO ( DATA TRANSFER OBJECT ) INTO BASICALLY
 * INTO THE USABLE DATA CLASS OBJECT I.E
 *
 **/
fun BookDto.toBook(): Book {
    return Book(
        id = _id,
        author = author,
        book_depository_stars = book_depository_stars,
        category = category,
        image = image,
        name = name,
        price = price,
    )
}

fun BookDto.toBookDetails(): BookDetails {
    return BookDetails(
         _id =_id,
        author = author,
        book_depository_stars =book_depository_stars,
        category = category,
        createdAt =createdAt,
        discount = discount,
        format =format,
        image = image,
        isbn = isbn,
        name = name,
        new_book = new_book,
        numOfReviews = numOfReviews,
        old_price = old_price,
        on_sale = on_sale,
        price = price,
        ratings = ratings,
        recent_comming = recent_comming,
        stock = stock,
        top_sellings = top_sellings
    )
}