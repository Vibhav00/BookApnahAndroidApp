package com.example.bookapnah.feature_user.presentation.user_sign

data class TextInputState (
    val isHintVisible:Boolean=true,
    val text:String="",
    val hint:String="",
    val focused:Boolean?=false
)