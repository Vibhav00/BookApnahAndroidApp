package com.example.bookapnah.feature_book.presentation.all_books


import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.example.bookapnah.core.domain.common.Resource
import com.example.bookapnah.feature_book.domain.user_cases.get_books.GetBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class AllBooksViewModel @Inject constructor(

    private val getBooksUseCase: GetBooksUseCase
) : ViewModel() {


    private val _stateBook = mutableStateOf(BooksListState())
    val stateBook: State<BooksListState> = _stateBook


    init {
        onEvent(BooksEvent.Search(null))
    }

    fun onEvent(event: BooksEvent) {
        when (event) {
            is BooksEvent.Categorize -> {
                if (stateBook.value.category.contains(event.category)) {
                    val k: MutableList<Category> = stateBook.value.category.toMutableList()
                    k.remove(event.category)
                    _stateBook.value = stateBook.value.copy(
                        category = k.toList(),
                        pageNo = 1
                    )
                } else {
                    val k: MutableList<Category> = stateBook.value.category.toMutableList()
                    k.add(event.category)
                    _stateBook.value = stateBook.value.copy(
                        category = k.toList(),
                        pageNo = 1
                    )
                }

                if (stateBook.value.category.contains(Category.NEW_BOOK) && stateBook.value.category.contains(
                        Category.TOP_SELLING
                    ) && stateBook.value.category.contains(Category.RECENT_COMING)
                ) {
                    getAllBooks(
                        keyword = stateBook.value.search,
                        recent = true,
                        topSelling = true,
                        newBook = true,
                        pageNo = 1
                    )
                }
                else if (stateBook.value.category.contains(Category.NEW_BOOK) && stateBook.value.category.contains(
                        Category.TOP_SELLING
                    )
                ) {
                    getAllBooks(
                        keyword = stateBook.value.search,
                        topSelling = true,
                        newBook = true,
                        pageNo = 1
                    )
                } else if (stateBook.value.category.contains(Category.NEW_BOOK) && stateBook.value.category.contains(
                        Category.RECENT_COMING
                    )
                ) {
                    getAllBooks(
                        keyword = stateBook.value.search,
                        recent = true,
                        newBook = true,
                        pageNo = 1
                    )
                } else if (stateBook.value.category.contains(Category.TOP_SELLING) && stateBook.value.category.contains(
                        Category.RECENT_COMING
                    )
                ) {
                    getAllBooks(
                        keyword = stateBook.value.search,
                        recent = true,
                        topSelling = true,
                        pageNo = 1
                    )
                } else if (stateBook.value.category.contains(Category.NEW_BOOK)) {
                    getAllBooks(keyword = stateBook.value.search, newBook = true, pageNo = 1)
                } else if (stateBook.value.category.contains(Category.TOP_SELLING)) {
                    getAllBooks(keyword = stateBook.value.search, topSelling = true, pageNo = 1)
                } else if (stateBook.value.category.contains(Category.RECENT_COMING)) {
                    getAllBooks(keyword = stateBook.value.search, recent = true, pageNo = 1)
                } else {
                    getAllBooks(keyword = stateBook.value.search, pageNo = 1)

                }
            }

            is BooksEvent.Search -> {
                _stateBook.value = stateBook.value.copy(
                    search = event.key,
                    pageNo = 1
                )


                getAllBooks(
                    pageNo = stateBook.value.pageNo,
                    newBook = stateBook.value.category.contains(Category.NEW_BOOK),
                    keyword = stateBook.value.search,
                    recent = stateBook.value.category.contains(Category.RECENT_COMING),
                    topSelling = stateBook.value.category.contains(Category.TOP_SELLING)
                )

            }

            is BooksEvent.ToggleCategorySection -> {

                _stateBook.value = stateBook.value.copy(
                    isCategoryVisible = !stateBook.value.isCategoryVisible
                )
            }

            is BooksEvent.Paginate -> {
                _stateBook.value = stateBook.value.copy(
                    pageNo = event.key
                )

                getAllBooks(
                    pageNo = event.key,
                    newBook = stateBook.value.category.contains(Category.NEW_BOOK),
                    keyword = stateBook.value.search,
                    recent = stateBook.value.category.contains(Category.RECENT_COMING),
                    topSelling = stateBook.value.category.contains(Category.TOP_SELLING)
                )
            }
        }
    }

    private fun getAllBooks(
        pageNo: Int = 1,
        newBook: Boolean = false,
        keyword: String? = null,
        recent: Boolean = false,
        topSelling: Boolean = false
    ) {
        getBooksUseCase(pageNo, newBook, keyword, recent, topSelling).onEach { result ->
            when (result) {
                is Resource.Success -> {

                    if (pageNo == 1) {
                        _stateBook.value = stateBook.value.copy(
                            books = result.data ?: emptyList()
                        )
                    } else {
                        _stateBook.value = stateBook.value.copy(
                            books = stateBook.value.books + result.data!!, pageNo = pageNo
                        )
                    }


                }

                is Resource.Error -> {
                    _stateBook.value = BooksListState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }

                is Resource.Loading -> {
                    _stateBook.value = BooksListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)

    }


    fun calcDominantColor(drawable: Drawable, onFinish: (Color) -> Unit , getSecondColor:(Color) -> Unit) {
        val bmp = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)

        Palette.from(bmp).generate { palette ->
            palette?.dominantSwatch ?.rgb?.let { colorValue ->
                onFinish(Color(colorValue))
            }
            palette?.vibrantSwatch ?.rgb?.let { colorValue ->
                getSecondColor(Color(colorValue))
            }
        }
    }

}