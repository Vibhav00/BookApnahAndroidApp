package com.example.bookapnah.core.presentation.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bookapnah.core.presentation.util.Screen
import com.example.bookapnah.feature_user.data.shared_perferences.PreferenceUtil
import com.example.bookapnah.feature_user.domain.model.UserDetailsData
import com.example.bookapnah.ui.theme.Roboto
import com.example.bookapnah.ui.theme.TypeIceLess
import com.example.bookapnah.ui.theme.fontColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


/**
 * HEADER OF THE NAVIGATION DRAWER
 **/
@Composable
fun NavigationHeader(
    navController:NavController,
    drawerState:DrawerState,
    scope:CoroutineScope,
    userState:MutableState<UserDetailsData>
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(TypeIceLess)
    ) {
        Row(
            modifier = Modifier.clickable {
                navController.navigate(Screen.UserScreen.route)
                scope.launch {
                    drawerState.close()
                }
            }
        ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                modifier = Modifier
                    .padding(start = 4.dp, end = 4.dp, top = 5.dp)
                    .size(80.dp),
                contentDescription = null,
                tint = fontColor
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1.5f)
                    .padding(start = 20.dp)
            ) {
                val output =
                    PreferenceUtil(LocalContext.current).get(
                        UserDetailsData::class,
                        PreferenceUtil.USER_DETAILS
                    )
                if (output != null) {
                    userState.value = output
                }
                Text(
                    text = userState.value.name.capitalize(),
                    fontSize = 20.sp,
                    fontFamily = Roboto,
                    color = fontColor
                )
                Text(
                    text = userState.value.username.capitalize(),
                    fontSize = 15.sp,
                    fontFamily = Roboto,
                    color = fontColor
                )

                Text(
                    text = userState.value.email.take(4) + "****" + userState.value.email.takeLast(
                        9
                    ),
                    fontSize = 10.sp,
                    fontFamily = Roboto,
                    color = fontColor
                )


            }
        }
    }


}