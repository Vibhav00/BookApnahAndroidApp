package com.example.bookapnah.core.presentation.util


/**
 * LIST OF DIFFERENT SCREENS NAVIGATE TO
 **/
sealed class Screen(val route: String) {
    data object BooksScreen: Screen("books_screen")
    data object DetailsBookScreen: Screen("details_book_screen")
    data object SavedBookScreen: Screen("saved_book_screen")
    data object CartBookScreen: Screen("cart_book_screen")
    data object UserScreen: Screen("user_screen")
    data object UrlScreen: Screen("url_screen")


}
