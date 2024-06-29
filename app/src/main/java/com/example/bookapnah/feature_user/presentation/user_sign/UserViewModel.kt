package com.example.bookapnah.feature_user.presentation.user_sign


import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapnah.core.domain.common.Resource
import com.example.bookapnah.feature_user.domain.model.SignInData
import com.example.bookapnah.feature_user.domain.model.SignUpData
import com.example.bookapnah.feature_user.domain.use_cases.sign_in.SignInUseCase
import com.example.bookapnah.feature_user.domain.use_cases.sign_up.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val signInUseCase: SignInUseCase,
    private val application: Application
) : ViewModel() {

    private val _signInUpState = mutableStateOf(SignInUpState())
    val signInUpState: State<SignInUpState> = _signInUpState


    fun onEvent(singInUpEvents: SignInUpEvents) {


        when (singInUpEvents) {
            is SignInUpEvents.TextChange -> {
                when (singInUpEvents.textEvents) {
                    TextEvents.EMAIL -> {
                        if (singInUpEvents.isFocused == null) {
                            _signInUpState.value = signInUpState.value.copy(
                                email = TextInputState(
                                    isHintVisible = false,
                                    text = singInUpEvents.text,
                                    focused = null,
                                    hint = "enter your email"
                                )
                            )
                        } else if (singInUpEvents.isFocused == true) {
                            _signInUpState.value = signInUpState.value.copy(
                                email = TextInputState(
                                    isHintVisible = signInUpState.value.email.text.isEmpty(),
                                    text = singInUpEvents.text,
                                    focused = true,
                                    hint = "enter your email"
                                )
                            )
                        } else {
                            _signInUpState.value = signInUpState.value.copy(
                                email = TextInputState(
                                    isHintVisible = signInUpState.value.email.text.isEmpty(),
                                    text = singInUpEvents.text,
                                    focused = signInUpState.value.email.focused,
                                    hint = "enter your email"
                                )
                            )
                        }


                    }

                    TextEvents.USERNAME -> {
                        if (singInUpEvents.isFocused == null) {
                            _signInUpState.value = signInUpState.value.copy(
                                username = TextInputState(
                                    isHintVisible = false,
                                    text = singInUpEvents.text,
                                    focused = null,
                                    hint = "enter your username"
                                )
                            )
                        } else if (singInUpEvents.isFocused == true) {
                            _signInUpState.value = signInUpState.value.copy(
                                username = TextInputState(
                                    isHintVisible = signInUpState.value.email.text.isEmpty(),
                                    text = singInUpEvents.text,
                                    focused = true,
                                    hint = "enter your username"
                                )
                            )
                        } else {
                            _signInUpState.value = signInUpState.value.copy(
                                username = TextInputState(
                                    isHintVisible = signInUpState.value.email.text.isEmpty(),
                                    text = singInUpEvents.text,
                                    focused = signInUpState.value.email.focused,
                                    hint = "enter your username"
                                )
                            )
                        }


                    }

                    TextEvents.PASSWORD -> {
                        if (singInUpEvents.isFocused == null) {
                            _signInUpState.value = signInUpState.value.copy(
                                password = TextInputState(
                                    isHintVisible = false,
                                    text = singInUpEvents.text,
                                    focused = null,
                                    hint = "enter your password"
                                )
                            )
                        } else if (singInUpEvents.isFocused == true) {
                            _signInUpState.value = signInUpState.value.copy(
                                password = TextInputState(
                                    isHintVisible = signInUpState.value.email.text.isEmpty(),
                                    text = singInUpEvents.text,
                                    focused = true,
                                    hint = "enter your password"
                                )
                            )
                        } else {
                            _signInUpState.value = signInUpState.value.copy(
                                password = TextInputState(
                                    isHintVisible = signInUpState.value.email.text.isEmpty(),
                                    text = singInUpEvents.text,
                                    focused = signInUpState.value.email.focused,
                                    hint = "enter your password"
                                )
                            )
                        }

                    }

                    TextEvents.NAME -> {
                        if (singInUpEvents.isFocused == null) {
                            _signInUpState.value = signInUpState.value.copy(
                                name = TextInputState(
                                    isHintVisible = false,
                                    text = singInUpEvents.text,
                                    focused = null,
                                    hint = "enter your name"
                                )
                            )
                        } else if (singInUpEvents.isFocused == true) {
                            _signInUpState.value = signInUpState.value.copy(
                                name = TextInputState(
                                    isHintVisible = signInUpState.value.email.text.isEmpty(),
                                    text = singInUpEvents.text,
                                    focused = true,
                                    hint = "enter your name"
                                )
                            )
                        } else {
                            _signInUpState.value = signInUpState.value.copy(
                                name = TextInputState(
                                    isHintVisible = signInUpState.value.email.text.isEmpty(),
                                    text = singInUpEvents.text,
                                    focused = signInUpState.value.email.focused,
                                    hint = "enter your name"
                                )
                            )
                        }

                    }

                }


            }

            is SignInUpEvents.ButtonClickEvents -> {
                when (singInUpEvents.buttonEvents) {
                    ButtonEvents.CHANGE -> {
                        _signInUpState.value = signInUpState.value.copy(
                            signIn = !_signInUpState.value.signIn
                        )
                    }

                    ButtonEvents.SIGN_IN -> {
                           signIn()
                    }

                    ButtonEvents.SIGN_UP -> {

                        signUp()
                    }
                }
            }

        }

    }

    private fun signUp() {

        val signUpData = SignUpData(
            signInUpState.value.name.text,
            signInUpState.value.email.text,
            signInUpState.value.password.text,
            signInUpState.value.username.text,

            )
        signUpUseCase(signUpData).onEach { result ->
            when (result) {
                is Resource.Success -> {

                    Toast.makeText(application,"Sign Up Successfully ",Toast.LENGTH_SHORT).show()
                    _signInUpState.value = signInUpState.value.copy(
                       success = true
                    )

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


    private fun signIn() {

        val signInData = SignInData(
            signInUpState.value.username.text,
            signInUpState.value.password.text,

            )
        signInUseCase(signInData).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    Toast.makeText(application,"Sign In  Successfully ",Toast.LENGTH_SHORT).show()
                    _signInUpState.value = signInUpState.value.copy(
                        success = true
                    )

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