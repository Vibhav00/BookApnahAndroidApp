package com.example.bookapnah.feature_book.presentation.saved

import com.example.bookapnah.feature_book.domain.model.BookDetails

sealed  class SavedBookEvent {
    data class  Delete(val bookDetails: BookDetails): SavedBookEvent()
}