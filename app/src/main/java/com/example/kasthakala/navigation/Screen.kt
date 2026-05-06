package com.example.kasthakala.navigation

sealed class Screen(val route: String) {

    object Home : Screen("home")

    object Catalog : Screen("catalog")

    object Estimator : Screen("estimator")

    object Quotation : Screen("quotation")

    object Portfolio : Screen("portfolio")

    object Favorites : Screen("favorites")
    object DesignDetail : Screen("design_detail")
}