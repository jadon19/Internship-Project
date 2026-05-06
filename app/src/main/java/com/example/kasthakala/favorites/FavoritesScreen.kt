package com.example.kasthakala.favorites

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Work

import androidx.compose.material3.*

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun FavoritesScreen(

    onNavigateToHome: () -> Unit,
    onNavigateToCatalog: () -> Unit,
    onNavigateToEstimator: () -> Unit,
    onNavigateToPortfolio: () -> Unit,

    viewModel: FavoritesViewModel = hiltViewModel()
) {

    val favorites by viewModel
        .favorites
        .collectAsState()

    Scaffold(

        bottomBar = {

            NavigationBar(
                containerColor = Color.White
            ) {

                NavigationBarItem(
                    selected = false,
                    onClick = onNavigateToHome,
                    icon = {
                        Icon(Icons.Default.Home, null)
                    },
                    label = {
                        Text("Home")
                    }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = onNavigateToCatalog,
                    icon = {
                        Icon(Icons.Default.GridView, null)
                    },
                    label = {
                        Text("Catalog")
                    }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = onNavigateToEstimator,
                    icon = {
                        Icon(Icons.Default.Calculate, null)
                    },
                    label = {
                        Text("Estimator")
                    }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = onNavigateToPortfolio,
                    icon = {
                        Icon(Icons.Default.Work, null)
                    },
                    label = {
                        Text("Portfolio")
                    }
                )

                NavigationBarItem(
                    selected = true,
                    onClick = {},
                    icon = {
                        Icon(Icons.Default.Favorite, null)
                    },
                    label = {
                        Text("Favorites")
                    }
                )
            }
        }

    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {

            Text(
                text = "Favorite Designs",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF7B4B2A)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Saved furniture designs",
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(24.dp))

            if (favorites.isEmpty()) {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = "No favorites added yet"
                    )
                }

            } else {

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {

                    items(favorites) { item ->

                        FavoriteCard(
                            item = item,
                            onDelete = {
                                viewModel.removeFavorite(item)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FavoriteCard(
    item: FavoriteEntity,
    onDelete: () -> Unit
) {

    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {

        Row(
            modifier = Modifier.padding(12.dp)
        ) {

            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = null,
                modifier = Modifier.size(110.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(14.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = item.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Saved furniture design",
                    color = Color.Gray
                )
            }

            IconButton(
                onClick = onDelete
            ) {

                Icon(
                    Icons.Default.Delete,
                    contentDescription = null,
                    tint = Color.Red
                )
            }
        }
    }
}