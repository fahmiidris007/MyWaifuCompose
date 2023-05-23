package com.fahmi.mywaifucompose.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object About : Screen("about_page")
    object Detail : Screen("home/{waifuId}") {
        fun createRoute(waifuId: String) = "home/$waifuId"
    }
}
