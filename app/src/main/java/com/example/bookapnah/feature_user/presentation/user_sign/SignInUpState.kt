package com.example.bookapnah.feature_user.presentation.user_sign

data class SignInUpState(
    val username:TextInputState = TextInputState(hint = "Enter your username"),
    val password:TextInputState = TextInputState(hint = "Enter your password"),
    val name:TextInputState = TextInputState(hint = "Enter your name"),
    val email:TextInputState = TextInputState( hint = "Enter your Email"),
    val signIn:Boolean = true,
    val success:Boolean = false
)