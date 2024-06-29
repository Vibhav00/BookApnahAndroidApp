package com.example.bookapnah.feature_user.presentation.user_sign

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Login
import androidx.compose.material.icons.outlined.ChangeCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bookapnah.core.presentation.util.Screen
import com.example.bookapnah.feature_user.presentation.user_sign.components.SearchBarCustom
import com.example.bookapnah.ui.theme.Roboto
import com.example.bookapnah.ui.theme.TypeIce
import com.example.bookapnah.ui.theme.TypeIceBtn
import com.example.bookapnah.ui.theme.fontColor
import com.example.bookapnah.ui.theme.lightGrey

@Composable
fun UserSignInUp(
    viewModel: UserViewModel = hiltViewModel(),
    navController: NavController,
    topPaddingValues: Dp,
    bottomPaddingValues: Dp,
) {

    val state = viewModel.signInUpState.value


    val defaultDominantColor = MaterialTheme.colorScheme.surface

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = bottomPaddingValues, top = topPaddingValues)
            .background(
                Brush.verticalGradient(
                    listOf(
                        defaultDominantColor, TypeIce, defaultDominantColor
                    )
                )
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .padding(20.dp)
                .shadow(10.dp, RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
                .background(
                    Brush.verticalGradient(
                        listOf(
                            defaultDominantColor, TypeIce, defaultDominantColor
                        )
                    )
                )
                .padding(30.dp)
        ) {

            Column(
                modifier = Modifier.wrapContentSize()
            ) {

                Text(
                    text = if (viewModel.signInUpState.value.signIn) "SIGN IN  " else "SIGN UP ",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, bottom = 20.dp),
                    textAlign = TextAlign.Center,
                    fontFamily = Roboto,
                    fontWeight = FontWeight.Bold,
                    color = fontColor
                )
                if (state.success) {
                    navController.navigate(Screen.BooksScreen.route)
                }

                if (!state.signIn) {
                    SearchBarCustom(
                        textInputState = state.email,
                        onSearch = {
                            viewModel.onEvent(
                                SignInUpEvents.TextChange(
                                    it,
                                    null,
                                    TextEvents.EMAIL
                                )
                            )
                        },
                        onFocus = {
                            viewModel.onEvent(
                                SignInUpEvents.TextChange(
                                    state.email.text,
                                    it,
                                    TextEvents.EMAIL
                                )
                            )
                        }

                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    SearchBarCustom(
                        textInputState = state.name,
                        onSearch = {
                            viewModel.onEvent(
                                SignInUpEvents.TextChange(
                                    it,
                                    null,
                                    TextEvents.NAME
                                )
                            )
                        },
                        onFocus = {
                            viewModel.onEvent(
                                SignInUpEvents.TextChange(
                                    state.name.text,
                                    true,
                                    TextEvents.NAME
                                )
                            )
                        }

                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }

                SearchBarCustom(
                    textInputState = state.username,
                    onSearch = {
                        viewModel.onEvent(
                            SignInUpEvents.TextChange(
                                it,
                                null,
                                TextEvents.USERNAME
                            )
                        )
                    },
                    onFocus = {
                        viewModel.onEvent(
                            SignInUpEvents.TextChange(
                                state.username.text,
                                true,
                                TextEvents.USERNAME
                            )
                        )
                    }

                )
                Spacer(modifier = Modifier.height(16.dp))

                SearchBarCustom(
                    textInputState = state.password,
                    onSearch = {
                        viewModel.onEvent(
                            SignInUpEvents.TextChange(
                                it,
                                null,
                                TextEvents.PASSWORD
                            )
                        )
                    },
                    onFocus = {
                        viewModel.onEvent(
                            SignInUpEvents.TextChange(
                                state.password.text,
                                true,
                                TextEvents.PASSWORD
                            )
                        )
                    }

                )
                Spacer(modifier = Modifier.height(16.dp))



                Spacer(modifier = Modifier.height(16.dp))
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = {
                            if (viewModel.signInUpState.value.signIn) {
                                viewModel.onEvent(SignInUpEvents.ButtonClickEvents(ButtonEvents.SIGN_IN))
                            } else {
                                viewModel.onEvent(SignInUpEvents.ButtonClickEvents(ButtonEvents.SIGN_UP))
                            }

                        },
                        modifier = Modifier
                            .weight(1f)
                            .shadow(10.dp, RoundedCornerShape(100.dp)),
                        colors = ButtonDefaults.buttonColors(
                            TypeIceBtn,
                            fontColor,
                            lightGrey,
                            Color.Black
                        )
                    ) {
                        if (viewModel.signInUpState.value.signIn) {
                            Icon(
                                imageVector = Icons.Default.Login,
                                modifier = Modifier
                                    .size(25.dp)
                                    .padding(start = 4.dp, end = 4.dp),
                                contentDescription = null
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Default.AccountCircle,
                                modifier = Modifier
                                    .size(25.dp)
                                    .padding(start = 4.dp, end = 4.dp),
                                contentDescription = null
                            )
                        }
                        Text(text = if (viewModel.signInUpState.value.signIn) "SIGN IN" else "SIGN UP")

                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(
                        onClick = {
                            viewModel.onEvent(SignInUpEvents.ButtonClickEvents(ButtonEvents.CHANGE))
                        },
                        modifier = Modifier
                            .weight(1f)
                            .shadow(10.dp, RoundedCornerShape(100.dp)),
                        colors = ButtonDefaults.buttonColors(
                            TypeIceBtn,
                            fontColor,
                            lightGrey,
                            Color.Black
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.ChangeCircle,
                            modifier = Modifier
                                .size(25.dp)
                                .padding(start = 4.dp, end = 4.dp),
                            contentDescription = null
                        )
                        Text(text = "Change ")

                    }
                }
            }

        }
    }

}