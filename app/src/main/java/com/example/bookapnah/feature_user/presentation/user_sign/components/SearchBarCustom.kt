package com.example.bookapnah.feature_user.presentation.user_sign.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.bookapnah.feature_user.presentation.user_sign.TextInputState
import com.example.bookapnah.ui.theme.Roboto
import com.example.bookapnah.ui.theme.fontColor

@Composable
fun SearchBarCustom(
    modifier: Modifier = Modifier,
    textInputState: TextInputState,
    onSearch: (String) -> Unit = {},
    onFocus:(Boolean) -> Unit = {}
) {


    Box(modifier = modifier) {
        BasicTextField(
            value = textInputState.text,
            onValueChange = {
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = fontColor,fontFamily = Roboto),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .onFocusChanged {
                    onFocus(it.isFocused)
                }
        )
        if (textInputState.isHintVisible) {
            Text(
                text = textInputState.hint,
                color = Color.LightGray,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 12.dp),
                fontFamily = Roboto
            )
        }
    }
}