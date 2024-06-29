package com.example.bookapnah.feature_book.domain.user_cases.get_local_books

import com.example.bookapnah.feature_book.domain.model.BookDetails
import com.example.bookapnah.feature_book.domain.repository.BooksRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocalBooksUseCase @Inject constructor(
    private  val repository: BooksRepository
) {
    operator fun invoke(): Flow<List<BookDetails>> {
        return repository.getLocalBooks()
        }
    }
