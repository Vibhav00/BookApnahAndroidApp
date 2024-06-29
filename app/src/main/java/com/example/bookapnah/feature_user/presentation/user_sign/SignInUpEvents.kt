package com.example.bookapnah.feature_user.presentation.user_sign

sealed class SignInUpEvents {
    data class TextChange(val text:String,val isFocused:Boolean?,val textEvents: TextEvents):SignInUpEvents()
    data class ButtonClickEvents(val buttonEvents: ButtonEvents):SignInUpEvents()

}