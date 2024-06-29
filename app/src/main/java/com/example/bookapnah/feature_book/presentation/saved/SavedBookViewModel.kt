package com.example.bookapnah.feature_book.presentation.saved

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import com.example.bookapnah.feature_book.domain.user_cases.get_local_books.GetLocalBooksUseCase
import javax.inject.Inject
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.example.bookapnah.feature_book.domain.model.BookDetails
import com.example.bookapnah.feature_book.domain.user_cases.delete_local_books.DeleteLocalBook
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class SavedBookViewModel @Inject constructor(
    private val getLocalBooksUseCase: GetLocalBooksUseCase,
    private val deleteLocalBook: DeleteLocalBook
) : ViewModel() {
    /** NOTES STATE **/
    private val _state = mutableStateOf(SavedBookState())


    /** variable that changes when _state changes and update ui **/
    val state: State<SavedBookState> = _state

    init {
        getAllBooks()
    }

    fun onEvent(savedBookEvent: SavedBookEvent) {
        when (savedBookEvent) {
            is SavedBookEvent.Delete -> {
                viewModelScope.launch {
                    deleteLocalBook(savedBookEvent.bookDetails)
                }

            }
        }

    }

    private fun getAllBooks() {
//        getBooksJob?.cancel()
        getLocalBooksUseCase().onEach {
            _state.value = state.value.copy(
                books = it
            )
        }.launchIn(viewModelScope)
    }



    fun calcDominantColor(drawable: Drawable, onFinish: (Color) -> Unit) {
        val bmp = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)

        Palette.from(bmp).generate { palette ->
            palette?.dominantSwatch?.rgb?.let { colorValue ->
                onFinish(Color(colorValue))
            }
        }
    }
}