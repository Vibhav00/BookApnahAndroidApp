package com.example.bookapnah.feature_book.presentation.detail_book.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.bookapnah.ui.theme.TypeIce

@Composable
fun BookTag(
    tag: String
) {
    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = TypeIce,
                shape = RoundedCornerShape(100.dp)
            )
            .padding(10.dp)
    ) {
        Text(
            text = tag,
            color = TypeIce,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}