package com.example.bookapnah.feature_book.presentation.all_books

sealed  class BooksEvent {
    data class Categorize(val category:Category):BooksEvent()
    data class Search(val key:String? = null):BooksEvent()
    data object  ToggleCategorySection:BooksEvent()
    data class Paginate(val key:Int):BooksEvent()
}