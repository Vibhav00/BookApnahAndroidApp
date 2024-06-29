package com.example.bookapnah.feature_book.domain.user_cases.get_books

import android.util.Log
import com.example.bookapnah.core.domain.common.Resource
import com.example.bookapnah.feature_book.data.remote.dto.books.toBook
import com.example.bookapnah.feature_book.domain.model.Book
import com.example.bookapnah.feature_book.domain.repository.BooksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import retrofit2.HttpException
import java.io.IOException

class GetBooksUseCase @Inject constructor(
    private val repository: BooksRepository
) {
    operator fun invoke(
        pageNo: Int = 1,
        newBook: Boolean = false,
        keyword: String? = null,
        recent: Boolean = false,
        topSelling: Boolean = false
    ): Flow<Resource<List<Book>>> = flow {
        try {
            // fetching all the books . after getting the response , we are converting
            // the response that is in List<BookDto> in List<Book>
            val books = repository.getBooks(pageNo, newBook, keyword, recent, topSelling)
                .map { it.toBook() }
            // emitting the response with success
            emit(Resource.Success<List<Book>>(books))

        } catch (e: HttpException) {
            // emitting the response with error
            emit(Resource.Error<List<Book>>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            // emitting the response with error
            emit(Resource.Error<List<Book>>("Couldn't reach server. Check your internet connection."))
        }
    }
}