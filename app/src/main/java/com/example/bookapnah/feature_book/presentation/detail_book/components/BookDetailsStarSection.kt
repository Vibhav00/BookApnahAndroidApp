package com.example.bookapnah.feature_book.presentation.detail_book.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookapnah.R
import com.example.bookapnah.ui.theme.Roboto
import com.example.bookapnah.ui.theme.fontColor

@Composable
fun BookDetailsStarSection(stars: Double) {
    var i = 1

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Row(
            modifier = Modifier.wrapContentSize()
        ) {
            while (i <= 5) {
                if (i < stars) {
                    Icon(
                        painter = painterResource(R.drawable.start_fill),
                        contentDescription = stringResource(id = R.string.icon_name),
                        modifier = Modifier.size(30.dp),
                        tint = fontColor
                    )
                } else {
                    Icon(
                        painter = painterResource(R.drawable.star_not_fill),
                        contentDescription = stringResource(id = R.string.icon_name),
                        modifier = Modifier.size(30.dp),
                        tint = fontColor
                    )
                }
                i++
            }
        }

        Text(
            text = stars.toString(),
            fontFamily = Roboto,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = fontColor
        )
    }


}