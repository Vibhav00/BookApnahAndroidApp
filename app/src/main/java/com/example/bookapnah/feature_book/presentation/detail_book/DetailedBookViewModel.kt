package com.example.bookapnah.feature_book.presentation.detail_book


import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapnah.core.domain.common.Resource
import com.example.bookapnah.feature_book.domain.model.CartItem
import com.example.bookapnah.feature_book.domain.user_cases.add_to_cart.AddToCartUseCase
import com.example.bookapnah.feature_book.domain.user_cases.get_book.GetBookUseCase
import com.example.bookapnah.feature_book.domain.user_cases.insert_local_books.InsertBookUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailedBookViewModel @Inject constructor(
    private val getBookUseCase: GetBookUseCase,
    private val addToCartUseCase: AddToCartUseCase,
    savedStateHandle: SavedStateHandle,
    private val insertBookUseCase: InsertBookUseCase,
    private val application: Application
) : ViewModel() {


    private val _state = mutableStateOf(BookDetailsState())
    val state: State<BookDetailsState> = _state

    init {

        savedStateHandle.get<String>("bookId")?.let {
            getBook(it)
        }
    }


    private fun getBook(id: String) {
        getBookUseCase(id).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = BookDetailsState(book = result.data)
                }

                is Resource.Error -> {
                    _state.value = BookDetailsState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }

                is Resource.Loading -> {
                    _state.value = BookDetailsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: BookDetailsEvents) {
        when (event) {
            is BookDetailsEvents.AddToCart -> {
                addToCart()
            }

            is BookDetailsEvents.AddToLocalDatabase -> {
                addToLocalDatabase()
            }
        }
    }

    private fun addToCart() {
        val data = state.value.book
        val cartItem = data?.let {
            CartItem(
                it.name, it.price.toString(), it.author, it.image, it.format

            )
        }
        if (cartItem != null) {
            addToCartUseCase(cartItem).onEach { res ->
                when (res) {
                    is Resource.Success -> {
                        Toast.makeText(
                            application, "added to cart successfully ", Toast.LENGTH_SHORT
                        ).show()

                    }

                    is Resource.Error -> {
                        Toast.makeText(application, "got some error ", Toast.LENGTH_SHORT).show()
                    }

                    is Resource.Loading -> {
                        Toast.makeText(application, "loading ", Toast.LENGTH_SHORT).show()
                    }
                }

            }.launchIn(viewModelScope)
        } else {

        }

    }


    private fun addToLocalDatabase() {
        viewModelScope.launch {
            try {
                state.value.book?.let { insertBookUseCase(it) }
                Toast.makeText(application, "saved  successfully ", Toast.LENGTH_SHORT).show()

            } catch (e: Exception) {

            }
        }
    }

}