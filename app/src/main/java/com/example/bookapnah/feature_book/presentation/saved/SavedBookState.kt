package com.example.bookapnah.feature_book.presentation.saved

import com.example.bookapnah.feature_book.domain.model.BookDetails

data class SavedBookState (    val books:List<BookDetails> = emptyList())
