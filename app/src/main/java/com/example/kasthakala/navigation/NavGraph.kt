package com.example.kasthakala.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kasthakala.catalog.CatalogScreen
import com.example.kasthakala.estimator.EstimatorScreen
import com.example.kasthakala.quotation.QuotationScreen
import com.example.kasthakala.portfolio.PortfolioScreen
import com.example.kasthakala.favorites.FavoritesScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Catalog.route) {
        composable(Screen.Catalog.route) {
            CatalogScreen(
                onNavigateToEstimator = { navController.navigate(Screen.Estimator.route) },
                onNavigateToPortfolio = { navController.navigate(Screen.Portfolio.route) },
                onNavigateToFavorites = { navController.navigate(Screen.Favorites.route) }
            )
        }
        composable(Screen.Estimator.route) {
            EstimatorScreen(onNavigateToQuote = { navController.navigate(Screen.Quotation.route) })
        }
        composable(Screen.Quotation.route) { QuotationScreen() }
        composable(Screen.Portfolio.route) { PortfolioScreen() }
        composable(Screen.Favorites.route) { FavoritesScreen() }
    }
}