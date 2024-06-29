package com.example.bookapnah.feature_user.presentation.user_details

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.example.bookapnah.feature_user.domain.use_cases.user_details.UserDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapnah.core.domain.common.Resource
import com.example.bookapnah.feature_user.presentation.user_sign.TextEvents
import com.example.bookapnah.feature_user.presentation.user_sign.TextInputState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val userDetailsUseCase: UserDetailsUseCase
) : ViewModel() {
    private val _stateUser = mutableStateOf(UserState())
    val stateUser: State<UserState> = _stateUser

    init {
        getUserDetails()
    }

    fun onEvent(userDetailsEvents: UserDetailsEvents) {

        when (userDetailsEvents) {
            is UserDetailsEvents.TextChange -> {
                when (userDetailsEvents.textEvents) {
                    TextEvents.EMAIL -> {
                        if (userDetailsEvents.isFocused == null) {
                            _stateUser.value = stateUser.value.copy(
                                email = TextInputState(
                                    isHintVisible = false,
                                    text = userDetailsEvents.text,
                                    focused = null,
                                    hint = "enter your email"
                                )
                            )
                        } else if (userDetailsEvents.isFocused == true) {
                            Log.e("eventTrigger", "focused true ")
                            _stateUser.value = stateUser.value.copy(
                                email = TextInputState(
                                    isHintVisible = stateUser.value.email.text.isEmpty(),
                                    text = userDetailsEvents.text,
                                    focused = true,
                                    hint = "enter your email"
                                )
                            )
                        } else {
                            Log.e("eventTrigger", "focused false ")
                            _stateUser.value = stateUser.value.copy(
                                email = TextInputState(
                                    isHintVisible = stateUser.value.email.text.isEmpty(),
                                    text = userDetailsEvents.text,
                                    focused = stateUser.value.email.focused,
                                    hint = "enter your email"
                                )
                            )
                        }
                    }

                    TextEvents.NAME -> {
                        if (userDetailsEvents.isFocused == null) {
                            _stateUser.value = stateUser.value.copy(
                                name = TextInputState(
                                    isHintVisible = false,
                                    text = userDetailsEvents.text,
                                    focused = null,
                                    hint = "enter your name"
                                )
                            )
                        } else if (userDetailsEvents.isFocused == true) {
                            Log.e("eventTrigger", "focused true ")
                            _stateUser.value = stateUser.value.copy(
                                name = TextInputState(
                                    isHintVisible = stateUser.value.name.text.isEmpty(),
                                    text = userDetailsEvents.text,
                                    focused = true,
                                    hint = "enter your name"
                                )
                            )
                        } else {
                            Log.e("eventTrigger", "focused false ")
                            _stateUser.value = stateUser.value.copy(
                                name = TextInputState(
                                    isHintVisible = stateUser.value.name.text.isEmpty(),
                                    text = userDetailsEvents.text,
                                    focused = stateUser.value.name.focused,
                                    hint = "enter your name"
                                )
                            )
                        }

                    }

                    TextEvents.USERNAME -> {
                        if (userDetailsEvents.isFocused == null) {
                            _stateUser.value = stateUser.value.copy(
                                username = TextInputState(
                                    isHintVisible = false,
                                    text = userDetailsEvents.text,
                                    focused = null,
                                    hint = "enter your username"
                                )
                            )
                        } else if (userDetailsEvents.isFocused == true) {
                            Log.e("eventTrigger", "focused true ")
                            _stateUser.value = stateUser.value.copy(
                                username = TextInputState(
                                    isHintVisible = stateUser.value.username.text.isEmpty(),
                                    text = userDetailsEvents.text,
                                    focused = true,
                                    hint = "enter your username"
                                )
                            )
                        } else {
                            Log.e("eventTrigger", "focused false ")
                            _stateUser.value = stateUser.value.copy(
                                username = TextInputState(
                                    isHintVisible = stateUser.value.username.text.isEmpty(),
                                    text = userDetailsEvents.text,
                                    focused = stateUser.value.username.focused,
                                    hint = "enter your username"
                                )
                            )
                        }

                    }

                    else -> {
                        // todo
                    }
                }

            }

            is UserDetailsEvents.ButtonClickEvents -> {
                when (userDetailsEvents.buttonEvents) {
                    UserDetailsButtonEvents.EDIT -> {
                        _stateUser.value = stateUser.value.copy(
                            editable = true
                        )
                    }

                    UserDetailsButtonEvents.LOGOUT -> {
                    // todo  @implement logout feature
                    }

                    UserDetailsButtonEvents.CANCEL -> {
                        _stateUser.value = stateUser.value.copy(
                            editable = false
                        )
                    }

                    UserDetailsButtonEvents.SAVE -> {
                    // todo
                    }
                }
            }
        }
    }

    private fun getUserDetails() {
        userDetailsUseCase().onEach {
            when (it) {
                is Resource.Success -> {
                    Log.e("userDetails", "this is called 9")
                    _stateUser.value = it.data?.let { it1 ->
                        stateUser.value.copy(
                            _id = it1._id,
                            createdAt = it1.createdAt,
                            email = TextInputState(text = it1.email),
                            name = TextInputState(text = it1.name),
                            username = TextInputState(text = it1.username)
                        )
                    }!!
                }

                is Resource.Error -> {
                // todo
                }

                is Resource.Loading -> {
                // todo
                }
            }
        }.launchIn(viewModelScope)

    }
}
