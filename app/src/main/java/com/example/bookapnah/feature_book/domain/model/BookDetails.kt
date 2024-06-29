package com.example.bookapnah.feature_book.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * ENTITY OF THE DATABASE
 **/
@Entity(tableName = "bookDetails")
data class BookDetails(
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
    val stock: Int,
    val top_sellings: Boolean,
    @PrimaryKey val id:Int?= null
)