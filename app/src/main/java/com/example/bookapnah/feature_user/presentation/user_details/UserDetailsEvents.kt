package com.example.bookapnah.feature_user.presentation.user_details

import com.example.bookapnah.feature_user.presentation.user_sign.ButtonEvents
import com.example.bookapnah.feature_user.presentation.user_sign.SignInUpEvents
import com.example.bookapnah.feature_user.presentation.user_sign.TextEvents

sealed class UserDetailsEvents {

    data class TextChange(val text:String,val isFocused:Boolean?,val textEvents: TextEvents):
        UserDetailsEvents()
    data class ButtonClickEvents(val buttonEvents: UserDetailsButtonEvents):   UserDetailsEvents()
}
