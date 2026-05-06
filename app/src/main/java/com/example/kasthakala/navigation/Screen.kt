package com.example.kasthakala.navigation

sealed class Screen(val route: String) {
    object Catalog : Screen("catalog")
    object Estimator : Screen("estimator")
    object Quotation : Screen("quotation")
    object Portfolio : Screen("portfolio")
    object Favorites : Screen("favorites")
}