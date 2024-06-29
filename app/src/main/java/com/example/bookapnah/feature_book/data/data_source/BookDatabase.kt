package com.example.bookapnah.feature_book.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bookapnah.feature_book.domain.model.BookDetails


/**
 * BOOK DATABASE FOR STORING BOOKS LOCALLY
 **/
@Database(
    entities = [BookDetails::class],
    version = 1
)
abstract class BookDatabase :RoomDatabase() {

 abstract val booksDao:BooksDao

    companion object {
        /** Name of the Database **/
        const val DATABASE_NAME = "books_db"
    }
}