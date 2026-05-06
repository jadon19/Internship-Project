package com.example.kasthakala.catalog

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CatalogScreen(
    onNavigateToEstimator: () -> Unit,
    onNavigateToPortfolio: () -> Unit,
    onNavigateToFavorites: () -> Unit,
    viewModel: CatalogViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = { Text("Kashta-Kala Catalog", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.headlineMedium) }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize()) {
            Row(modifier = Modifier.fillMaxWidth().padding(8.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
                Button(onClick = onNavigateToEstimator) { Text("Estimator") }
                Button(onClick = onNavigateToPortfolio) { Text("Portfolio") }
                Button(onClick = onNavigateToFavorites) { Text("Favorites") }
            }

            LazyColumn {
                item {
                    DesignItem("Modern Bed", onFavorite = { viewModel.toggleFavorite("1", "Modern Bed") })
                    DesignItem("Wooden Sofa", onFavorite = { viewModel.toggleFavorite("2", "Wooden Sofa") })
                }
            }
        }
    }
}

@Composable
fun DesignItem(title: String, onFavorite: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, style = MaterialTheme.typography.titleLarge)
            Button(onClick = onFavorite, modifier = Modifier.padding(top = 8.dp)) {
                Text("Save to Favorites")
            }
        }
    }
}