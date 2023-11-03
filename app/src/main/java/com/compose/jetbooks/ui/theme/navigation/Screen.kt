package com.compose.jetbooks.ui.theme.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object About : Screen("about")
    object Detail: Screen("home/{bookId}"){
        fun createRoute(bookId: Long) = "home/$bookId"
    }
}