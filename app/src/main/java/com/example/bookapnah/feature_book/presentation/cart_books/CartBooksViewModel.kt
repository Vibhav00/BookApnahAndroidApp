package com.example.bookapnah.feature_book.presentation.cart_books

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.bookapnah.feature_book.domain.user_cases.get_all_cart.GetAllCartItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.State
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.example.bookapnah.core.domain.common.Resource
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


@HiltViewModel
class CartBooksViewModel @Inject constructor(
    val getAllCartItemsUseCase: GetAllCartItemsUseCase
) : ViewModel() {


    // mutable private version of the book state
    private val _stateBook = mutableStateOf(CartBookState())

    // immutable public version of the book state
    val stateBook: State<CartBookState> = _stateBook

    init {
        getAllCartBooks()
    }

    private fun getAllCartBooks() {
        getAllCartItemsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _stateBook.value = result.data?.let {
                        stateBook.value.copy(
                            books = it.cartItems
                        )
                    }!!
                }

                is Resource.Error -> {
                    _stateBook.value = result.data?.let {
                        stateBook.value.copy(
                            error = "got some error "
                        )
                    }!!
                }

                is Resource.Loading -> {
                    _stateBook.value = result.data?.let {
                        stateBook.value.copy(
                            isLoading = true
                        )
                    }!!
                }
            }
        }.launchIn(viewModelScope)

    }


    // function to calculate dominant color
    fun calcDominantColor(drawable: Drawable, onFinish: (Color) -> Unit) {
        val bmp = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)
        Palette.from(bmp).generate { palette ->
            palette?.dominantSwatch?.rgb?.let { colorValue ->
                onFinish(Color(colorValue))
            }
        }
    }
}