package com.example.bookapnah.core.presentation.main.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bookapnah.core.presentation.main.MenuItem
import com.example.bookapnah.core.presentation.util.Screen
import com.example.bookapnah.feature_book.presentation.all_books.AllBooks
import com.example.bookapnah.feature_book.presentation.cart_books.CartBook
import com.example.bookapnah.feature_book.presentation.detail_book.DetailedBook
import com.example.bookapnah.feature_book.presentation.saved.SavedBook
import com.example.bookapnah.feature_user.data.shared_perferences.PreferenceUtil
import com.example.bookapnah.feature_user.domain.model.UserDetailsData
import com.example.bookapnah.feature_user.presentation.user_details.UserDetails
import com.example.bookapnah.feature_user.presentation.user_sign.UserSignInUp
import com.example.bookapnah.ui.theme.BookApnahTheme
import com.example.bookapnah.ui.theme.Roboto
import com.example.bookapnah.ui.theme.TypeIce
import com.example.bookapnah.ui.theme.TypeIceBtn
import com.example.bookapnah.ui.theme.TypeIceLess
import com.example.bookapnah.ui.theme.fontColor
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainComposable(
) {


    val userState = mutableStateOf(
      MenuItem.defaultInput
    )

    BookApnahTheme {



        /**
         * CREATING THE INSTANCE OF THE SHARED PREFERENCE
         **/
        val preferenceUtil = PreferenceUtil(LocalContext.current)

        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Red)
        ) {
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val scope = rememberCoroutineScope()
            var selectedItemIndex by rememberSaveable {
                mutableIntStateOf(0)
            }
            var selectedItemIndexBottom by rememberSaveable {
                mutableIntStateOf(1)
            }
            val defaultDominantColor = MaterialTheme.colorScheme.surface


            val navController = rememberNavController()
            ModalNavigationDrawer(
                scrimColor = Color.Transparent,
                modifier = Modifier.background(Color.Transparent),
                drawerContent = {
                    ModalDrawerSheet(
                        modifier = Modifier.background(
                            Brush.linearGradient(
                                listOf(TypeIce, defaultDominantColor)
                            )
                        ),
                        drawerContainerColor = Color.Transparent,
                        drawerContentColor = Color.Red,
                    ) {

                        Spacer(modifier = Modifier.height(16.dp))
                        NavigationHeader(
                            navController = navController,
                            drawerState = drawerState,
                            scope = scope,
                            userState = userState
                        )

                       MenuItem.listOfNavigationDrawerItems .forEachIndexed { index, item ->
                            NavigationDrawerItem(
                                label = {
                                    Text(text = item.title)
                                },
                                selected = index == selectedItemIndex,
                                onClick = {
//                                    item.nav?.let { navController.navigate(it) }
                                    selectedItemIndex = index
                                    scope.launch {
                                        drawerState.close()
                                    }
                                },
                                icon = {
                                    Icon(
                                        imageVector = if (index == selectedItemIndex) {
                                            item.selectedIcon
                                        } else item.unselectedIcon,
                                        contentDescription = item.title
                                    )
                                },
                                badge = {
                                    item.badgeCount?.let {
                                        Text(text = item.badgeCount.toString())
                                    }
                                },
                                modifier = Modifier
                                    .padding(NavigationDrawerItemDefaults.ItemPadding),
                                colors = NavigationDrawerItemDefaults.colors(
                                    unselectedIconColor = fontColor,
                                    unselectedContainerColor = Color.Transparent,
                                    unselectedTextColor = fontColor,
                                    selectedIconColor = fontColor,
                                    selectedContainerColor = TypeIceLess,
                                    selectedTextColor = fontColor
                                )
                            )
                        }

                    }
                },
                drawerState = drawerState,
            ) {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = buildAnnotatedString {
                                        withStyle(
                                            style = SpanStyle(
                                                color = TypeIceBtn,
                                                fontSize = 30.sp
                                            )
                                        ) {
                                            append("B")
                                        }
                                        append("ook  ")
                                        withStyle(
                                            style = SpanStyle(
                                                color = TypeIceBtn,
                                                fontSize = 30.sp
                                            )
                                        ) {
                                            append("A")
                                        }
                                        append("pnah")
                                    },
                                    fontFamily = Roboto,
                                    fontWeight = FontWeight.Bold,
                                    color = fontColor
                                )
                            },
                            modifier = Modifier.background(
                                Brush.verticalGradient(
                                    listOf(TypeIce, defaultDominantColor)
                                )
                            ),
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color.Transparent
                            ),

                            navigationIcon = {
                                IconButton(onClick = {
                                    scope.launch {
                                        drawerState.open()
                                    }
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Menu,
                                        contentDescription = "Menu"
                                    )
                                }
                            },

                            )
                    },
                    bottomBar = {
                        NavigationBar(
                            modifier = Modifier.background(
                                Brush.verticalGradient(
                                    listOf(defaultDominantColor, TypeIce)
                                )
                            ),
                            containerColor = Color.Transparent,
                            contentColor = Color.Magenta
                        ) {
                            MenuItem.bottomNavigationItems.forEachIndexed { index, item ->
                                NavigationBarItem(
                                    modifier = Modifier.background(Color.Transparent),
                                    selected = selectedItemIndexBottom == index,
                                    onClick = {
                                        selectedItemIndexBottom = index

                                        item.nav?.let { navController.navigate(it) }
                                    },
                                    label = {
                                        Text(text = item.title)
                                    },
                                    alwaysShowLabel = true,
                                    icon = {
                                        BadgedBox(
                                            badge = {
                                                if (item.badgeCount != null) {
                                                    Badge {
                                                        Text(text = item.badgeCount.toString())
                                                    }
                                                } else if (item.hasNews) {
                                                    Badge()
                                                }
                                            }
                                        ) {
                                            Icon(
                                                imageVector = if (index == selectedItemIndex) {
                                                    item.selectedIcon
                                                } else item.unselectedIcon,
                                                contentDescription = item.title
                                            )
                                        }
                                    },
                                    colors = NavigationBarItemDefaults.colors(
                                        selectedTextColor = fontColor,
                                        unselectedIconColor = fontColor,
                                        selectedIconColor = Color.White,
                                        indicatorColor = TypeIceBtn
                                    ),
                                )
                            }
                        }
                    },
                ) {

                    NavHost(
                        navController = navController,
                        startDestination = Screen.BooksScreen.route
                    ) {

                        composable(route = Screen.BooksScreen.route) { k ->

                            val output =
                                PreferenceUtil(LocalContext.current).get(
                                    UserDetailsData::class,
                                    PreferenceUtil.USER_DETAILS
                                )
                            if (output != null) {
                                userState.value = output
                            }

                            AllBooks(
                                navController = navController,
                                topPaddingValues = it.calculateTopPadding(),
                                bottomPaddingValues = it.calculateBottomPadding()
                            )
                        }

                        composable(
                            route = Screen.CartBookScreen.route
                        ) { _ ->
                            CartBook(
                                topPadding = it.calculateTopPadding(),
                                bottomPadding = it.calculateBottomPadding()
                            )
                        }
                        composable(
                            route = Screen.SavedBookScreen.route
                        ) { _ ->
                            SavedBook(
                                topPadding = it.calculateTopPadding(),
                                bottomPadding = it.calculateBottomPadding()
                            )
                        }
                        composable(
                            route = Screen.DetailsBookScreen.route + "?bookId={bookId}&dominantColor={dominantColor}&secondColor={secondColor}",
                            arguments = listOf(
                                navArgument("dominantColor") {
                                    type = NavType.IntType
                                },
                                navArgument("secondColor") {
                                    type = NavType.IntType
                                },
                                navArgument(
                                    name = "bookId"
                                ) {
                                    type = NavType.StringType
                                    defaultValue = ""
                                },
                            )
                        ) { arg ->
                            val color = arg.arguments?.getInt("dominantColor") ?: -1
                            val scolor = arg.arguments?.getInt("secondColor") ?: -1
                            DetailedBook(
                                dominantColor = color,
                                topPaddingValues = it.calculateTopPadding(),
                                bottomPaddingValues = it.calculateBottomPadding(),
                                secondColor = scolor
                            )
                        }
                        composable(
                            route = Screen.UserScreen.route
                        ) { _ ->
                            if (preferenceUtil != null) {
                                if (preferenceUtil.login)
                                    UserDetails(
                                        navController = navController,
                                        topPaddingValues = it.calculateTopPadding(),
                                        bottomPaddingValues = it.calculateBottomPadding(),
                                    )
                                else
                                    UserSignInUp(
                                        navController = navController,
                                        topPaddingValues = it.calculateTopPadding(),
                                        bottomPaddingValues = it.calculateBottomPadding(),
                                    )
                            }
                        }

                    }
                }
            }

        }
    }
}
