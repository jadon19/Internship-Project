package com.example.kasthakala.portfolio

import android.net.Uri

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Work

import androidx.compose.material3.*

import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.hilt.navigation.compose.hiltViewModel

import coil.compose.AsyncImage

@Composable
fun PortfolioScreen(

    onNavigateToHome: () -> Unit,
    onNavigateToCatalog: () -> Unit,
    onNavigateToEstimator: () -> Unit,
    onNavigateToFavorites: () -> Unit,

    viewModel: PortfolioViewModel = hiltViewModel()
) {

    val portfolioItems by viewModel
        .portfolioItems
        .collectAsState()

    var title by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val imagePickerLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri ->

            selectedImageUri = uri
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
                    selected = true,
                    onClick = {},
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

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),

            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {

            item {

                // HEADER CARD

                Card(
                    shape = RoundedCornerShape(28.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Transparent
                    )
                ) {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                brush = Brush.horizontalGradient(
                                    listOf(
                                        Color(0xFF7B4B2A),
                                        Color(0xFFB46A35)
                                    )
                                )
                            )
                            .padding(24.dp)
                    ) {

                        Column {

                            Text(
                                text = "My Portfolio",
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                text = "Showcase your completed furniture projects and impress customers.",
                                color = Color(0xFFFFE8D6),
                                lineHeight = 22.sp
                            )
                        }
                    }
                }
            }

            item {

                // ADD PROJECT CARD

                Card(
                    shape = RoundedCornerShape(24.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 8.dp
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFFFFBF7)
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(18.dp)
                    ) {

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Icon(
                                Icons.Default.Work,
                                contentDescription = null,
                                tint = Color(0xFFB46A35)
                            )

                            Spacer(modifier = Modifier.width(10.dp))

                            Text(
                                text = "Add New Project",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Spacer(modifier = Modifier.height(18.dp))

                        OutlinedTextField(
                            value = title,
                            onValueChange = {
                                title = it
                            },
                            modifier = Modifier.fillMaxWidth(),
                            label = {
                                Text("Project Title")
                            },
                            shape = RoundedCornerShape(16.dp)
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        OutlinedTextField(
                            value = description,
                            onValueChange = {
                                description = it
                            },
                            modifier = Modifier.fillMaxWidth(),
                            label = {
                                Text("Project Description")
                            },
                            shape = RoundedCornerShape(16.dp)
                        )

                        Spacer(modifier = Modifier.height(18.dp))

                        OutlinedButton(
                            onClick = {

                                imagePickerLauncher.launch("image/*")
                            },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp)
                        ) {

                            Icon(
                                Icons.Default.Image,
                                contentDescription = null
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Text("Upload Project Image")
                        }

                        selectedImageUri?.let { uri ->

                            Spacer(modifier = Modifier.height(18.dp))

                            AsyncImage(
                                model = uri,
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                                    .clip(RoundedCornerShape(20.dp)),
                                contentScale = ContentScale.Crop
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Button(
                            onClick = {

                                if (
                                    title.isNotBlank() &&
                                    description.isNotBlank() &&
                                    selectedImageUri != null
                                ) {

                                    viewModel.addProject(

                                        PortfolioEntity(
                                            projectTitle = title,
                                            description = description,
                                            imageUri = selectedImageUri.toString()
                                        )
                                    )

                                    title = ""
                                    description = ""
                                    selectedImageUri = null
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),

                            shape = RoundedCornerShape(18.dp),

                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFB46A35)
                            )
                        ) {

                            Icon(
                                Icons.Default.Add,
                                contentDescription = null
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                text = "Add Project",
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }

            item {

                Text(
                    text = "Completed Projects",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF7B4B2A)
                )
            }

            if (portfolioItems.isEmpty()) {

                item {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 60.dp),
                        contentAlignment = Alignment.Center
                    ) {

                        Text(
                            text = "No projects added yet",
                            color = Color.Gray,
                            fontSize = 18.sp
                        )
                    }
                }

            } else {

                items(portfolioItems) { item ->

                    PortfolioCard(item)
                }
            }
        }
    }
}

@Composable
fun PortfolioCard(
    item: PortfolioEntity
) {

    Card(
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {

        Column {

            AsyncImage(
                model = item.imageUri,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.padding(18.dp)
            ) {

                Text(
                    text = item.projectTitle,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF3E2723)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = item.description,
                    color = Color.DarkGray,
                    lineHeight = 22.sp
                )
            }
        }
    }
}