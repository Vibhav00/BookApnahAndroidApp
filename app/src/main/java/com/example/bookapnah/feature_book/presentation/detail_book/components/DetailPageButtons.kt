package com.example.bookapnah.feature_book.presentation.detail_book.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bookapnah.feature_book.presentation.detail_book.BookDetailsEvents
import com.example.bookapnah.feature_book.presentation.detail_book.DetailedBookViewModel
import com.example.bookapnah.ui.theme.TypeIceBtn
import com.example.bookapnah.ui.theme.fontColor
import com.example.bookapnah.ui.theme.lightGrey

@Composable
fun DetailPageButtons(viewmodel: DetailedBookViewModel = hiltViewModel()) {


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = {
                viewmodel.onEvent(BookDetailsEvents.AddToCart)

            },
            modifier = Modifier
                .weight(1f)
                .shadow(10.dp, RoundedCornerShape(100.dp)),
            colors = ButtonDefaults.buttonColors(TypeIceBtn, fontColor, lightGrey, Color.Black)
        ) {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                modifier = Modifier.padding(start = 4.dp, end = 4.dp),
                contentDescription = null
            )
            Text(text = "CART ")

        }
        Spacer(modifier = Modifier.width(16.dp))
        Button(
            onClick = {
                viewmodel.onEvent(BookDetailsEvents.AddToLocalDatabase)
            },
            modifier = Modifier
                .weight(1f)
                .shadow(10.dp, RoundedCornerShape(100.dp)),
            colors = ButtonDefaults.buttonColors(TypeIceBtn, fontColor, lightGrey, Color.Black)
        ) {
            Icon(
                imageVector = Icons.Default.Save,
                modifier = Modifier.padding(start = 4.dp, end = 4.dp),
                contentDescription = null
            )
            Text(text = " SAVE ")

        }
    }

}