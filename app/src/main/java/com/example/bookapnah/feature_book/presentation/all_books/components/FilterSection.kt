package com.example.bookapnah.feature_book.presentation.all_books.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
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
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.bookapnah.core.presentation.main.components.DefaultRadioButton
import com.example.bookapnah.feature_book.presentation.all_books.Category
import com.example.bookapnah.ui.theme.Roboto
import com.example.bookapnah.ui.theme.TypeIce
import com.example.bookapnah.ui.theme.fontColor


/**
 *
 * This is the dropdown section for
 * filtering the list
 *
 **/
@Composable
fun FilterSection(
    modifier: Modifier = Modifier,
    selectedItems: List<Category> = emptyList(),
    onOrderChange: (Category) -> Unit,
    onSearch: (String) -> Unit
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
            .background(Brush.verticalGradient(
                listOf(Color.White,TypeIce,)
            ))
    ) {

        Column(
            modifier = modifier
                .wrapContentSize()
                .padding(16.dp)

        ) {
            SearchBar(
                onSearch = {
                    onSearch(it)
                }
            )
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                    modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                DefaultRadioButton(
                    text = "Recent",
                    selected = selectedItems.contains(Category.RECENT_COMING),
                    /** updating title **/
                    /** updating title **/
                    onSelect = {
                        onOrderChange(Category.RECENT_COMING)
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                DefaultRadioButton(
                    text = "New",
                    selected = selectedItems.contains(Category.NEW_BOOK),
                    onSelect = {
                        onOrderChange(Category.NEW_BOOK)
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                DefaultRadioButton(
                    text = "Top",
                    selected = selectedItems.contains(Category.TOP_SELLING),
                    onSelect = {
                        Log.e("beldih", "this is done ")
                        onOrderChange(Category.TOP_SELLING)
                    }
                )
            }
        }



    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {}
) {
    var text by remember {
        mutableStateOf("")
    }
    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }

    Box(modifier = modifier) {
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = fontColor, fontFamily = Roboto),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .onFocusChanged {
                    Log.e("focusState", it.isFocused.toString())
                    isHintDisplayed = it.isFocused && text.isEmpty()
                }
        )
        if (isHintDisplayed) {
            Text(
                text = hint,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 12.dp),
                color = fontColor, fontFamily = Roboto
            )
        }
    }
}