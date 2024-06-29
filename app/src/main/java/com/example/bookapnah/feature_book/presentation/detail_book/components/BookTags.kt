package com.example.bookapnah.feature_book.presentation.detail_book.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bookapnah.feature_book.domain.model.BookDetails

@Composable
fun BookTags(bookDetails: BookDetails) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (bookDetails.new_book) {
            BookTag(tag = "new book")
            Spacer(modifier = Modifier.width(10.dp))
        }
        if (bookDetails.recent_comming) {
            BookTag(tag = "recent coming ")
            Spacer(modifier = Modifier.width(10.dp))
        }
        if (bookDetails.on_sale) {
            BookTag(tag = "on sale ")
            Spacer(modifier = Modifier.width(10.dp))
        }
        if (bookDetails.top_sellings) {
            BookTag(tag = "top sellings ")
            Spacer(modifier = Modifier.width(10.dp))
        }
    }
}
