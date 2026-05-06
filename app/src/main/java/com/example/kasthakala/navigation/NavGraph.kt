package com.example.kasthakala.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kasthakala.catalog.DesignDetailScreen
import com.example.kasthakala.catalog.CatalogScreen
import com.example.kasthakala.estimator.EstimatorScreen
import com.example.kasthakala.favorites.FavoritesScreen
import com.example.kasthakala.home.HomeScreen
import com.example.kasthakala.portfolio.PortfolioScreen
import com.example.kasthakala.quotation.QuotationScreen

@Composable
fun NavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {

        // HOME

        composable(Screen.Home.route) {
            HomeScreen(

                onNavigateToCatalog = {
                    navController.navigate(Screen.Catalog.route)
                },

                onNavigateToEstimator = {
                    navController.navigate(Screen.Estimator.route)
                },

                onNavigateToPortfolio = {
                    navController.navigate(Screen.Portfolio.route)
                },

                onNavigateToFavorites = {
                    navController.navigate(Screen.Favorites.route)
                }
            )
        }

        // CATALOG

        // CATALOG

        composable(Screen.Catalog.route) {

            CatalogScreen(

                navController = navController,

                onNavigateToHome = {
                    navController.navigate(Screen.Home.route)
                },

                onNavigateToEstimator = {
                    navController.navigate(Screen.Estimator.route)
                },

                onNavigateToPortfolio = {
                    navController.navigate(Screen.Portfolio.route)
                },

                onNavigateToFavorites = {
                    navController.navigate(Screen.Favorites.route)
                }
            )
        }

        // ESTIMATOR

        composable(Screen.Estimator.route) {
            EstimatorScreen(

                onNavigateToHome = {
                    navController.navigate(Screen.Home.route)
                },

                onNavigateToCatalog = {
                    navController.navigate(Screen.Catalog.route)
                },

                onNavigateToPortfolio = {
                    navController.navigate(Screen.Portfolio.route)
                },

                onNavigateToFavorites = {
                    navController.navigate(Screen.Favorites.route)
                },

                onNavigateToQuote = {
                    navController.navigate(Screen.Quotation.route)
                }
            )
        }

        // QUOTATION

        composable(Screen.Quotation.route) {

            QuotationScreen()
        }

        // PORTFOLIO

        // PORTFOLIO

        composable(Screen.Portfolio.route) {

            PortfolioScreen(

                onNavigateToHome = {
                    navController.navigate(Screen.Home.route)
                },

                onNavigateToCatalog = {
                    navController.navigate(Screen.Catalog.route)
                },

                onNavigateToEstimator = {
                    navController.navigate(Screen.Estimator.route)
                },

                onNavigateToFavorites = {
                    navController.navigate(Screen.Favorites.route)
                }
            )
        }

        composable("${Screen.DesignDetail.route}/{title}/{price}/{image}") { backStackEntry ->

            val title =
                backStackEntry.arguments?.getString("title") ?: ""

            val price =
                backStackEntry.arguments?.getString("price") ?: ""

            val image =
                backStackEntry.arguments?.getString("image")?.toIntOrNull()
                    ?: 0

            DesignDetailScreen(
                title = title,
                price = price,
                imageRes = image,
                onNavigateToEstimator = {
                    navController.navigate(Screen.Estimator.route)
                }
            )
        }

        composable(Screen.Favorites.route) {

            FavoritesScreen(

                onNavigateToHome = {
                    navController.navigate(Screen.Home.route)
                },

                onNavigateToCatalog = {
                    navController.navigate(Screen.Catalog.route)
                },

                onNavigateToEstimator = {
                    navController.navigate(Screen.Estimator.route)
                },

                onNavigateToPortfolio = {
                    navController.navigate(Screen.Portfolio.route)
                }
            )
        }
    }
}