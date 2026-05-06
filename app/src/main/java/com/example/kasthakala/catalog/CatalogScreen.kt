package com.example.kasthakala.catalog

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items

import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Work

import androidx.compose.material3.*

import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.hilt.navigation.compose.hiltViewModel

import androidx.navigation.NavController

import com.example.kasthakala.R
import com.example.kasthakala.favorites.FavoriteEntity
import com.example.kasthakala.favorites.FavoritesViewModel
import com.example.kasthakala.navigation.Screen

data class FurnitureItem(
    val title: String,
    val image: Int,
    val price: String
)

@Composable
fun CatalogScreen(

    navController: NavController,

    onNavigateToHome: () -> Unit,
    onNavigateToEstimator: () -> Unit,
    onNavigateToPortfolio: () -> Unit,
    onNavigateToFavorites: () -> Unit,

    viewModel: FavoritesViewModel = hiltViewModel()
) {

    val furnitureList = listOf(

        FurnitureItem(
            "Modern Bed",
            R.drawable.bed,
            "₹25,000"
        ),

        FurnitureItem(
            "Luxury Sofa",
            R.drawable.sofa,
            "₹40,000"
        ),

        FurnitureItem(
            "Wooden Chair",
            R.drawable.chair,
            "₹8,000"
        ),

        FurnitureItem(
            "TV Unit",
            R.drawable.tv,
            "₹18,000"
        ),

        FurnitureItem(
            "Wardrobe",
            R.drawable.wardrobe,
            "₹35,000"
        ),

        FurnitureItem(
            "Premium Bed",
            R.drawable.bed,
            "₹30,000"
        )
    )

    var searchText by remember {
        mutableStateOf("")
    }

    val filteredList = furnitureList.filter {

        it.title.contains(searchText, true)
    }

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
                    selected = true,
                    onClick = {},
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
                    selected = false,
                    onClick = onNavigateToFavorites,
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
                text = "Furniture Catalog",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF7B4B2A)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Browse modern furniture designs",
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(20.dp))

            // SEARCH

            OutlinedTextField(
                value = searchText,
                onValueChange = {
                    searchText = it
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text("Search furniture...")
                },
                leadingIcon = {
                    Icon(Icons.Default.Search, null)
                },
                shape = RoundedCornerShape(18.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // GRID

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(14.dp),
                horizontalArrangement = Arrangement.spacedBy(14.dp),
                modifier = Modifier.fillMaxSize()
            ) {

                items(filteredList) { furniture ->

                    FurnitureCard(

                        furniture = furniture,

                        onClick = {

                            navController.navigate(

                                "${Screen.DesignDetail.route}" +
                                        "/${furniture.title}" +
                                        "/${furniture.price}" +
                                        "/${furniture.image}"
                            )
                        },

                        onAddFavorite = {

                            viewModel.addFavorite(

                                FavoriteEntity(
                                    designId = furniture.title,
                                    title = furniture.title,
                                    imageRes = furniture.image
                                )
                            )
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun FurnitureCard(
    furniture: FurnitureItem,
    onClick: () -> Unit,
    onAddFavorite: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {

        Column {

            Image(
                painter = painterResource(id = furniture.image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.padding(12.dp)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {

                    IconButton(
                        onClick = onAddFavorite
                    ) {

                        Icon(
                            Icons.Default.Favorite,
                            contentDescription = null,
                            tint = Color.Red
                        )
                    }
                }

                Text(
                    text = furniture.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = furniture.price,
                    color = Color(0xFFB46A35),
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = onClick,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFB46A35)
                    )
                ) {

                    Text("View")
                }
            }
        }
    }
}
