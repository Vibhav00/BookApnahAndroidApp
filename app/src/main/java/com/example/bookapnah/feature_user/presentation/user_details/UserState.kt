package com.example.bookapnah.feature_user.presentation.user_details

import com.example.bookapnah.feature_user.presentation.user_sign.TextInputState

data class UserState(
    val _id: String = "",
    val createdAt: String = "",
    val email: TextInputState =  TextInputState(hint = "Enter your username"),
    val name: TextInputState =  TextInputState(hint = "Enter your username"),
    val username:TextInputState =  TextInputState(hint = "Enter your username"),
    val editable:Boolean  = false,
    val logOut:Boolean = false
)
