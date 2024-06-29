package com.example.bookapnah.feature_book.domain.user_cases.get_book

import android.util.Log
import com.example.bookapnah.core.domain.common.Resource
import com.example.bookapnah.feature_book.data.remote.dto.books.toBookDetails
import com.example.bookapnah.feature_book.domain.model.BookDetails
import com.example.bookapnah.feature_book.domain.repository.BooksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetBookUseCase @Inject constructor(
    private  val repository: BooksRepository
) {
    operator fun invoke(id :String): Flow<Resource<BookDetails>> = flow {

        try{
            // getting the details of unique book
            val books = repository.getBook(id).book.toBookDetails()
            // emitting the response with success
            emit(Resource.Success<BookDetails>(books))

        }catch(e: HttpException) {
            // emitting the response with error
            emit(Resource.Error<BookDetails>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            // emitting the response with error
            emit(Resource.Error<BookDetails>("Couldn't reach server. Check your internet connection."))
        }
    }
}