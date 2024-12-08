package com.example.bookapnah.feature_book.data.remote

import com.example.bookapnah.feature_book.data.remote.dto.books.BookDetailResDto
import com.example.bookapnah.feature_book.data.remote.dto.books.BookResDto
import com.example.bookapnah.feature_book.data.remote.dto.cart.CartBooks
import com.example.bookapnah.feature_book.domain.model.CartItem
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface BooksApi {
    @GET("/books")
    suspend fun  getAllBooks(
        @Query("page")
        page:Int = 1,
        @Query("newbook")
        newBook:Boolean =false,
        @Query("keyword")
        keyword:String? =null,
        @Query("recentcomming")
        recent:Boolean=false,
        @Query("topsellings")
        topSelling:Boolean = false
    ):BookResDto



    @GET("/books/book/{id}")
    suspend fun  getBookDetails(
          @Path("id") id:String
    ):BookDetailResDto

    @POST("/users/addtocart")
    suspend fun addToCart(
        @Body cartItem:CartItem,
        @Query("token")
        token:String,
    ):AddCartResDto

    @GET("/users/getAllCart")
    suspend fun  getAllCartItems(
        @Query("token")
        token:String,
    ):CartBooks

    @DELETE("/users/deleteCartItem")
    suspend fun removeFromCart(
        @Query("name") bookName:String,
        @Query("token")
        token:String,
    ):Unit


}