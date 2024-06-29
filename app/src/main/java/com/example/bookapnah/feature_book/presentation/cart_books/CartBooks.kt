package com.example.bookapnah.feature_book.presentation.cart_books

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bookapnah.feature_book.presentation.cart_books.components.BookItemCart
import com.example.bookapnah.ui.theme.Roboto
import com.example.bookapnah.ui.theme.TypeIce
import com.example.bookapnah.ui.theme.fontColor

@Composable
fun CartBook(
    viewModel: CartBooksViewModel = hiltViewModel(), topPadding: Dp, bottomPadding: Dp
) {

    // book state
    val bookList = viewModel.stateBook.value.books

    // default dominant color
    val defaultDominantColor = MaterialTheme.colorScheme.surface


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = topPadding, bottom = bottomPadding)
            .background(
                Brush.verticalGradient(
                    listOf(
                        defaultDominantColor, TypeIce, defaultDominantColor
                    )
                )
            )
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = " Cart Books  ",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 20.sp,
            fontFamily = Roboto,
            fontWeight = FontWeight.Bold,
            color = fontColor,
            textAlign = TextAlign.Center
        )

        bookList.forEach {
            BookItemCart(cartItemRes = it)
        }

    }

}