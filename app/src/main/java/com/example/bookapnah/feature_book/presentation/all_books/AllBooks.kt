package com.example.bookapnah.feature_book.presentation.all_books

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FilterAlt
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bookapnah.feature_book.presentation.all_books.components.BookList
import com.example.bookapnah.feature_book.presentation.all_books.components.FilterSection
import com.example.bookapnah.ui.theme.TypeIce
import com.example.bookapnah.ui.theme.TypeIceBtn
import com.example.bookapnah.ui.theme.fontColor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AllBooks(
    viewModel: AllBooksViewModel = hiltViewModel(),
    navController: NavController,
    topPaddingValues: Dp,
    bottomPaddingValues: Dp
) {

    val state = viewModel.stateBook.value
    val defaultDominantColor = MaterialTheme.colorScheme.surface

    Surface(
        color = Color.Red,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = topPaddingValues, bottom = bottomPaddingValues)
    ) {


        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        viewModel.onEvent(BooksEvent.ToggleCategorySection)
                    }, containerColor = TypeIceBtn
                ) {
                    Icon(
                        imageVector = Icons.Outlined.FilterAlt,
                        contentDescription = "Save note",
                        tint = fontColor
                    )
                }
            },
        ) {
            Column(
                modifier = Modifier.background(
                    Brush.verticalGradient(
                        listOf(
                            defaultDominantColor, TypeIce, defaultDominantColor
                        )
                    )
                )
            ) {

                BookList(navController = navController)
            }
        }

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            AnimatedVisibility(
                visible = state.isCategoryVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                FilterSection(
                    modifier = Modifier.fillMaxWidth(),
                    selectedItems = state.category,
                    onSearch = {
                        viewModel.onEvent(BooksEvent.Search(it))
                    },
                    onOrderChange = {
                        viewModel.onEvent(BooksEvent.Categorize(it))
                    },

                    )
            }

        }

    }

}
