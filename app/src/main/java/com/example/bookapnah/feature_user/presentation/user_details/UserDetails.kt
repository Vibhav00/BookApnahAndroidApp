package com.example.bookapnah.feature_user.presentation.user_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bookapnah.core.presentation.util.Screen
import com.example.bookapnah.feature_user.presentation.user_details.components.UserDetailsBar
import com.example.bookapnah.feature_user.presentation.user_sign.TextEvents
import com.example.bookapnah.ui.theme.Roboto
import com.example.bookapnah.ui.theme.TypeIce
import com.example.bookapnah.ui.theme.TypeIceBtn
import com.example.bookapnah.ui.theme.fontColor
import com.example.bookapnah.ui.theme.lightGrey

@Composable
fun UserDetails(
    viewModel: UserDetailsViewModel = hiltViewModel(),
    navController: NavController,
    topPaddingValues: Dp,
    bottomPaddingValues: Dp,
) {

    val state = viewModel.stateUser.value


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
                    text = if (state.editable) "UPDATE USER   " else "USER DETAILS ",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, bottom = 20.dp),
                    textAlign = TextAlign.Center,
                    fontFamily = Roboto,
                    fontWeight = FontWeight.Bold,
                    color = fontColor
                )
                if (state.logOut) {
                    navController.navigate(Screen.UserScreen.route)
                }


                UserDetailsBar(
                    textInputState = state.email,
                    onSearch = {
                        viewModel.onEvent(
                            UserDetailsEvents.TextChange(
                                it,
                                null,
                                TextEvents.EMAIL

                            )
                        )
                    },
                    onFocus = {
                        viewModel.onEvent(
                            UserDetailsEvents.TextChange(
                                state.email.text,
                                it,
                                TextEvents.EMAIL
                            )
                        )
                    },
                    focusable = state.editable,
                    name = "Email"
                )
                UserDetailsBar(
                    textInputState = state.name,
                    onSearch = {
                        viewModel.onEvent(
                            UserDetailsEvents.TextChange(
                                it,
                                null,
                                TextEvents.NAME

                            )
                        )
                    },
                    onFocus = {
                        viewModel.onEvent(
                            UserDetailsEvents.TextChange(
                                state.name.text,
                                it,
                                TextEvents.NAME
                            )
                        )
                    },
                    focusable = state.editable,
                    name = "Name"
                )
                UserDetailsBar(
                    textInputState = state.username,
                    onSearch = {
                        viewModel.onEvent(
                            UserDetailsEvents.TextChange(
                                it,
                                null,
                                TextEvents.USERNAME

                            )
                        )
                    },
                    onFocus = {
                        viewModel.onEvent(
                            UserDetailsEvents.TextChange(
                                state.username.text,
                                it,
                                TextEvents.USERNAME
                            )
                        )
                    },
                    focusable = state.editable,
                    name = "Username"
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                ) {
                    Button(
                        onClick = {
                            if (state.editable) {
                                viewModel.onEvent(
                                    UserDetailsEvents.ButtonClickEvents(
                                        UserDetailsButtonEvents.SAVE
                                    )
                                )
                            } else {
                                viewModel.onEvent(
                                    UserDetailsEvents.ButtonClickEvents(
                                        UserDetailsButtonEvents.EDIT
                                    )
                                )
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
                        if (state.editable) {
                            Icon(
                                imageVector = Icons.Default.Save,
                                modifier = Modifier
                                    .size(25.dp)
                                    .padding(start = 4.dp, end = 4.dp),
                                contentDescription = null
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                modifier = Modifier
                                    .size(25.dp)
                                    .padding(start = 4.dp, end = 4.dp),
                                contentDescription = null
                            )
                        }
                        Text(text = if (state.editable) "SAVE " else "EDIT ")

                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(
                        onClick = {
                            if (state.editable) {
                                viewModel.onEvent(
                                    UserDetailsEvents.ButtonClickEvents(
                                        UserDetailsButtonEvents.CANCEL
                                    )
                                )
                            } else {
                                viewModel.onEvent(
                                    UserDetailsEvents.ButtonClickEvents(
                                        UserDetailsButtonEvents.LOGOUT
                                    )
                                )
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
                        if (state.editable) {
                            Icon(
                                imageVector = Icons.Default.Cancel,
                                modifier = Modifier
                                    .size(25.dp)
                                    .padding(start = 4.dp, end = 4.dp),
                                contentDescription = null
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Default.Logout,
                                modifier = Modifier
                                    .size(25.dp)
                                    .padding(start = 4.dp, end = 4.dp),
                                contentDescription = null
                            )
                        }
                        Text(text = if (state.editable) "CANCEL " else "LOGOUT")

                    }
                }
            }


        }
    }
}
