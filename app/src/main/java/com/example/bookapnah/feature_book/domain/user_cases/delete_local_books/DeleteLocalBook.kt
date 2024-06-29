package com.example.bookapnah.feature_book.domain.user_cases.delete_local_books

import android.util.Log
import com.example.bookapnah.core.domain.common.Constants
import com.example.bookapnah.feature_book.domain.model.BookDetails
import com.example.bookapnah.feature_book.domain.repository.BooksRepository
import javax.inject.Inject

class DeleteLocalBook @Inject constructor(
    private  val repository: BooksRepository
) {
    suspend operator fun invoke(bookDetails: BookDetails){
        try{
            repository.deleteBook(bookDetails)
        }catch (e : Exception){
            Log.e(Constants.DATABASE_TAG,"GOT AN EXCEPTION = ${e}")
        }
    }
}