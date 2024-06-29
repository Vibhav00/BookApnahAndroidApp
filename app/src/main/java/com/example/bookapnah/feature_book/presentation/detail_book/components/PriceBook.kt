package com.example.bookapnah.feature_book.presentation.detail_book.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CurrencyRupee
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookapnah.ui.theme.Roboto
import com.example.bookapnah.ui.theme.fontColor


@Composable
fun PriceBook(newPrice: Int, oldPrice: Int, discount: Int) {
    Row(
        modifier = Modifier.wrapContentSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.CurrencyRupee,
            modifier = Modifier
                .size(30.dp)
                .padding(start = 4.dp, end = 4.dp),
            contentDescription = null
        )
        Text(
            text = newPrice.toString(),
            fontFamily = Roboto,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = fontColor
        )
        Spacer(modifier = Modifier.width(15.dp))
        Text(
            text = oldPrice.toString(),
            fontFamily = Roboto,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            style = TextStyle(textDecoration = TextDecoration.LineThrough, color = fontColor)
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = "${discount.toString()}%off",
            fontFamily = Roboto,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = fontColor
        )
    }

}
