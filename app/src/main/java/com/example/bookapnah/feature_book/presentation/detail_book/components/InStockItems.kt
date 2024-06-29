package com.example.bookapnah.feature_book.presentation.detail_book.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.bookapnah.ui.theme.Roboto
import com.example.bookapnah.ui.theme.fontColor
import com.example.bookapnah.ui.theme.playwite


@Composable
fun InStockItems(inStock: Int) {
    Row(
        modifier = Modifier.wrapContentSize(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${inStock}",
            fontSize = 30.sp, fontFamily = Roboto, fontWeight = FontWeight.Bold, color = fontColor
        )

        Text(text = " items are Available ", fontFamily = playwite, color = fontColor)
    }

}
