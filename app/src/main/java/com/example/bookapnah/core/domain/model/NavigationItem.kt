package com.example.bookapnah.core.domain.model

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeCount: Int? = null,
    val nav: String? = null
)


/**
 *
 **/
//    NavigationItem(
//        title = "Update Api Address ",
//        selectedIcon = Icons.Filled.Settings,
//        unselectedIcon = Icons.Outlined.Settings,
//        badgeCount = 45,
//        nav = Screen.UserScreen.route
//        hasNews = true,
//    ),