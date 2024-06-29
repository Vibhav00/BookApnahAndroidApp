package com.example.bookapnah.feature_book.presentation.cart_books.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CurrencyRupee
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookapnah.feature_book.data.remote.dto.cart.CartItemRes
import com.example.bookapnah.feature_book.presentation.cart_books.CartBooksViewModel
import com.example.bookapnah.ui.theme.Roboto
import com.example.bookapnah.ui.theme.TypeIceBtn
import com.example.bookapnah.ui.theme.autography
import com.example.bookapnah.ui.theme.fontColor
import com.example.bookapnah.ui.theme.lightGrey
import java.util.Locale

@Composable
fun BookItemCart(
    cartItemRes: CartItemRes,
    onDeleteClick: (CartItemRes) -> Unit = {},
    viewModel: CartBooksViewModel = hiltViewModel()
) {
    val defaultDominantColor = MaterialTheme.colorScheme.surface

    var dominantColor by remember {
        mutableStateOf(defaultDominantColor)
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(2.5f)
            .padding(5.dp),
        contentAlignment = Alignment.CenterStart
    ) {


        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(start = 50.dp)
                .shadow(10.dp, RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
                .background(
                    Brush.horizontalGradient(
                        listOf(
                            defaultDominantColor, dominantColor,
                        ),
                    )
                )
        ) {
            Column(
                modifier = Modifier.padding(start = 45.dp, top = 10.dp, bottom = 10.dp, end = 10.dp)
            ) {
                Text(
                    text = "${
                        cartItemRes.name.capitalize(
                            Locale.ROOT
                        ).take(15)
                    }",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    color = fontColor,
                    fontFamily = Roboto
                )
                Text(
                    text = cartItemRes.author,
                    fontFamily = autography,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal,
                    color = fontColor
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    val newPrice = cartItemRes.price
                    Icon(
                        imageVector = Icons.Default.CurrencyRupee,
                        modifier = Modifier
                            .size(30.dp)
                            .padding(start = 4.dp, end = 4.dp),
                        contentDescription = null,
                        tint = fontColor
                    )
                    Text(
                        text = newPrice.toString(),
                        fontFamily = Roboto,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(10f),
                        color = fontColor
                    )
                    Button(
                        onClick = {
                            onDeleteClick(cartItemRes)
                        },
                        modifier = Modifier
                            .wrapContentSize()
                            .shadow(25.dp, RoundedCornerShape(500.dp)),
                        colors = ButtonDefaults.buttonColors(
                            TypeIceBtn, fontColor, lightGrey, Color.Black
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            modifier = Modifier.padding(start = 4.dp, end = 4.dp),
                            contentDescription = null
                        )
                        Text(text = "Remove")

                    }
                }

            }
        }
        Box(
            contentAlignment = Alignment.CenterStart,

            ) {
            AsyncImage(model = ImageRequest.Builder(LocalContext.current).data(cartItemRes.image)
                .crossfade(true).build(),
                contentDescription = cartItemRes.name,
                modifier = Modifier.size(100.dp),
                onSuccess = {
                    viewModel.calcDominantColor(it.result.drawable) {
                        dominantColor = it
                    }
                })
        }
    }
}
