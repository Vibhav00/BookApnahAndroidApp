package com.example.bookapnah.feature_book.presentation.all_books.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookapnah.core.presentation.util.Screen
import com.example.bookapnah.feature_book.domain.model.Book
import com.example.bookapnah.feature_book.presentation.all_books.AllBooksViewModel
import com.example.bookapnah.ui.theme.Roboto
import com.example.bookapnah.ui.theme.autography
import com.example.bookapnah.ui.theme.fontColor


/**
 *
 * Main composable for each composable items
 * @includes
 * image
 * text fields
 *
 **/
@Composable
fun BookEntry(
    entry: Book,
    modifier: Modifier = Modifier,
    viewModel: AllBooksViewModel = hiltViewModel(),
    navController: NavController
) {
    val defaultDominantColor = MaterialTheme.colorScheme.surface

    var dominantColor by remember {
        mutableStateOf(defaultDominantColor)
    }
    var secondColor by remember {
        mutableStateOf(defaultDominantColor)
    }
    var showProgress by remember {
        mutableStateOf(true)
    }
    Box(contentAlignment = Alignment.TopCenter,
        modifier = modifier
            .shadow(5.dp, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .aspectRatio(.7f)
            .background(
                Brush.verticalGradient(
                    listOf(
                        dominantColor, defaultDominantColor,
                    )
                )
            )
            .padding(top = 10.dp)
            .clickable {
                navController.navigate(
                    Screen.DetailsBookScreen.route + "?bookId=${entry.id}&dominantColor=${dominantColor.toArgb()}&secondColor=${secondColor.toArgb()}"
                )
            }) {
        Column {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(entry.image).crossfade(true)
                    .build(), contentDescription = entry.name, onSuccess = {
                    viewModel.calcDominantColor(it.result.drawable, {
                        dominantColor = it
                    }, {
                        secondColor = it
                    })
                    showProgress = false
                }, modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.CenterHorizontally)
            )
            if (showProgress) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.scale(0.5f),

                    )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.padding(
                    start = 10.dp, end = 10.dp
                )
            ) {
                BookDetailsStartSection(stars = entry.book_depository_stars)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.padding(
                    start = 10.dp, end = 10.dp
                )
            ) {

                Text(
                    text = entry.name.take(15),
                    fontSize = 15.sp,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.weight(1F),
                    fontFamily = Roboto,
                    fontWeight = FontWeight.Bold,
                    color = fontColor
                )

            }
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier.padding(
                    start = 10.dp, end = 10.dp
                )
            ) {
                Text(
                    text = entry.author,
                    fontFamily = autography,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Start,
                    color = fontColor
                )

            }
        }
    }
}