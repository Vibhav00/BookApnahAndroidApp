package com.example.bookapnah.feature_book.presentation.detail_book

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CurrencyRupee
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.substring
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookapnah.R
import com.example.bookapnah.feature_book.domain.model.BookDetails
import com.example.bookapnah.feature_book.presentation.detail_book.components.BookDetailsStarSection
import com.example.bookapnah.feature_book.presentation.detail_book.components.BookDetailsTopSection
import com.example.bookapnah.feature_book.presentation.detail_book.components.BookTag
import com.example.bookapnah.feature_book.presentation.detail_book.components.BookTags
import com.example.bookapnah.feature_book.presentation.detail_book.components.DetailPageButtons
import com.example.bookapnah.feature_book.presentation.detail_book.components.InStockItems
import com.example.bookapnah.feature_book.presentation.detail_book.components.PriceBook
import com.example.bookapnah.feature_user.data.shared_perferences.PreferenceUtil
import com.example.bookapnah.feature_user.presentation.user_sign.ButtonEvents
import com.example.bookapnah.feature_user.presentation.user_sign.SignInUpEvents
import com.example.bookapnah.ui.theme.Roboto
import com.example.bookapnah.ui.theme.TypeIce
import com.example.bookapnah.ui.theme.TypeIceBtn
import com.example.bookapnah.ui.theme.TypeWhite
import com.example.bookapnah.ui.theme.autography
import com.example.bookapnah.ui.theme.fontColor
import com.example.bookapnah.ui.theme.lightGrey
import com.example.bookapnah.ui.theme.playwite
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import java.util.Locale

@Composable
fun DetailedBook(
    dominantColor: Int,
    topPaddingValues: Dp,
    bottomPaddingValues: Dp,
    topPadding: Dp = 20.dp,
    pokemonImageSize: Dp = 100.dp,
    viewModel: DetailedBookViewModel = hiltViewModel(),
    secondColor: Int
) {

    val defaultDominantColor = MaterialTheme.colorScheme.surface
    val bookState = viewModel.state.value
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(secondColor), defaultDominantColor,
                    )
                )
            )
            .padding(bottom = bottomPaddingValues, top = topPaddingValues)
    ) {

        BookDetailsTopSection(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f)
                .align(Alignment.TopCenter)
        )
        BooksDetailStateWrapper(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = topPadding + pokemonImageSize / 2f,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                )

                .shadow(10.dp, RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
                .background(
                    Brush.verticalGradient(
                        listOf(
                            defaultDominantColor, Color(dominantColor)
                        )
                    )
                )
                .padding(16.dp)
                .align(Alignment.BottomCenter),
            loadingModifier = Modifier
                .size(100.dp)
                .align(Alignment.Center)
                .padding(
                    top = topPadding + pokemonImageSize / 2f,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                ),
            dominantColor = secondColor

        )

        Box(
            contentAlignment = Alignment.TopCenter, modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = topPadding, start = 16.dp, end = 16.dp, bottom = 16.dp
                )
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(bookState.book?.image)
                    .crossfade(true).build(),
                contentDescription = bookState.book?.name,
                modifier = Modifier.size(140.dp)
            )
        }
    }
}


@Composable
fun BooksDetailStateWrapper(
    modifier: Modifier = Modifier,
    loadingModifier: Modifier = Modifier,
    viewModel: DetailedBookViewModel = hiltViewModel(),
    dominantColor: Int
) {
    val bookState = viewModel.state.value
    if (bookState.isLoading) {

    } else {
        bookState.book?.let {
            BooksDetailsSection(
                bookDetails = it, modifier = modifier.offset(y = (-20).dp)
            )
        }
    }
}

@Composable
fun BooksDetailsSection(
    bookDetails: BookDetails,
    modifier: Modifier,
) {
    val scrollState = rememberScrollState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .offset(y = 100.dp)
            .verticalScroll(scrollState)
    ) {
        Text(
            text = "@${bookDetails._id.takeLast(3)} ",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            color = fontColor,
            fontFamily = Roboto
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "${bookDetails.name.capitalize(Locale.ROOT).take(20)}",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            color = fontColor,
            fontFamily = Roboto
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "🖊${bookDetails.author}",
            fontFamily = autography,
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            color = fontColor
        )
        BookDetailsStarSection(stars = bookDetails.book_depository_stars)
        InStockItems(inStock = bookDetails.stock)
        PriceBook(
            newPrice = bookDetails.price,
            oldPrice = bookDetails.old_price,
            discount = bookDetails.discount
        )
        Divider(
            modifier = Modifier.padding(top = 10.dp), color = fontColor
        )
        BookTags(bookDetails = bookDetails)
        Divider(
            modifier = Modifier.padding(top = 10.dp), color = fontColor
        )
        DetailPageButtons()
    }
}






