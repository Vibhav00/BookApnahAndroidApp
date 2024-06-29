package com.example.bookapnah.feature_book.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bookapnah.feature_book.domain.model.BookDetails
import kotlinx.coroutines.flow.Flow

/**
 * BOOKS DATA ACCESS OBJECT
 **/
@Dao
interface BooksDao {
    // get all books
    @Query("SELECT * FROM bookDetails ")
    fun getAllBooks():Flow<List<BookDetails>>

    // update + insert book
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(bookDetails: BookDetails)

    // deleting book
    @Delete
    suspend fun deleteBook(bookDetails: BookDetails)
}