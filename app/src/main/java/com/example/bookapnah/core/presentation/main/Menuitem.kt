package com.example.bookapnah.core.presentation.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.bookapnah.core.domain.model.BottomNavigationItem
import com.example.bookapnah.core.domain.model.NavigationItem
import com.example.bookapnah.core.presentation.util.Screen
import com.example.bookapnah.feature_user.domain.model.UserDetailsData

class MenuItem(){
    companion object{
        /**
     * LIST OF ALL SIDE  NAVIGATION ITEM
     **/
        val listOfNavigationDrawerItems =  listOf(
                NavigationItem(
                    title = "Update Api Address ",
                    selectedIcon = Icons.Filled.Settings,
                    unselectedIcon = Icons.Outlined.Settings,
                    nav = Screen.UserScreen.route,
                ),
            )

        /**
         * LIST OF ALL BOTTOM NAVIGATION ITEM
         **/
        val bottomNavigationItems = listOf(
            BottomNavigationItem(
                title = "Cart",
                selectedIcon = Icons.Filled.ShoppingCart,
                unselectedIcon = Icons.Outlined.ShoppingCart,
                hasNews = false,
                nav = Screen.CartBookScreen.route
            ),
            BottomNavigationItem(
                title = "Books",
                selectedIcon = Icons.Filled.Book,
                unselectedIcon = Icons.Outlined.Book,
                hasNews = false,
                nav = Screen.BooksScreen.route
            ),
            BottomNavigationItem(
                title = "Saved",
                selectedIcon = Icons.Filled.Save,
                unselectedIcon = Icons.Outlined.Save,
                hasNews = false,
                nav = Screen.SavedBookScreen.route
            ),
        )

        val defaultInput =   UserDetailsData("dk", emptyList(), ",", "-----------","ENTER TO REGISTER ", "CLICK HERE ")

    }
}