package com.example.bookapnah.core.presentation.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.bookapnah.core.presentation.main.components.MainComposable
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity(
) : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            /**
             * MAIN COMPOSABLE CONTAINS ALL SIDE NAVIGATION
             * ,BOTTOM NAVIGATION , TOP BAR , AND ALL NAV CONTROLLERS
             *
             **/
            MainComposable()
        }
    }
}
