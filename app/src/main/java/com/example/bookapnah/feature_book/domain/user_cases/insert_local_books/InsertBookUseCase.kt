package com.example.bookapnah.feature_book.domain.user_cases.insert_local_books

import com.example.bookapnah.feature_book.domain.model.BookDetails
import com.example.bookapnah.feature_book.domain.repository.BooksRepository
import javax.inject.Inject

class InsertBookUseCase @Inject constructor(
    private  val repository: BooksRepository,
) {
        suspend operator fun invoke(bookDetails: BookDetails) {
            return repository.insertBook(bookDetails)
        }
}