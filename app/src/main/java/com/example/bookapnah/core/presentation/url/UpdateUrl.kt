package com.example.bookapnah.core.presentation.url

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp


/**
 * COMPOSABLE FOR  CHANGING THE URL , ONLY FOR DEVELOPERS
 **/
@Composable
fun UpdateUrl() {
    Box (modifier = Modifier.fillMaxSize().background(Color.Red)){
        Text(text = "this is text for url update", modifier = Modifier.padding(100.dp))
    }
}