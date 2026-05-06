
package com.example.kasthakala.estimator

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Engineering
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Forest
import androidx.compose.material.icons.filled.FormatPaint
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Straighten
import androidx.compose.material.icons.filled.Work
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kasthakala.shared.SharedEstimatorViewModel
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MenuAnchorType

import androidx.compose.material3.*

import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EstimatorScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToCatalog: () -> Unit,
    onNavigateToPortfolio: () -> Unit,
    onNavigateToFavorites: () -> Unit,
    onNavigateToQuote: () -> Unit
) {

    // DIMENSIONS

    var length by remember { mutableStateOf("") }
    var width by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }

    // WOOD TYPES

    val woodTypes = listOf(
        "Teak Wood",
        "Plywood",
        "Oak Wood",
        "Rosewood",
        "MDF",
        "Particle Board"
    )

    var expanded by remember { mutableStateOf(false) }
    var selectedWood by remember { mutableStateOf(woodTypes[0]) }

    // QUANTITY

    var quantity by remember { mutableStateOf("1") }

    // EXTRA COSTS

    var laborCost by remember { mutableStateOf("") }
    var polishCost by remember { mutableStateOf("") }
    var transportCost by remember { mutableStateOf("") }

    // CALCULATIONS

    // CALCULATIONS

    val lengthValue =
        length.toFloatOrNull() ?: 0f

    val widthValue =
        width.toFloatOrNull() ?: 0f

    val heightValue =
        height.toFloatOrNull() ?: 0f

    val quantityValue =
        quantity.toIntOrNull() ?: 1

// TOTAL WOOD VOLUME

    val volume =
        lengthValue *
                widthValue *
                heightValue

// WOOD RATE

    val woodRate = when (selectedWood) {

        "Teak Wood" -> 2500f
        "Plywood" -> 1200f
        "Oak Wood" -> 2200f
        "Rosewood" -> 3000f
        "MDF" -> 900f
        else -> 700f
    }

// WOOD COST

    val woodCost =
        volume *
                woodRate *
                quantityValue

// EXTRA COSTS

    val labor =
        laborCost.toFloatOrNull() ?: 0f

    val polish =
        polishCost.toFloatOrNull() ?: 0f

    val transport =
        transportCost.toFloatOrNull() ?: 0f

// SUBTOTAL

    val subtotal =
        woodCost +
                labor +
                polish +
                transport

// GST

    val gst =
        subtotal * 0.18f

// FINAL COST

    val totalCost =
        subtotal + gst

// SHARED VIEWMODEL

    val sharedViewModel: SharedEstimatorViewModel =
        viewModel()

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
                    selected = true,
                    onClick = {},
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
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .padding(16.dp)
        ) {

            // TITLE

            Text(
                text = "Material Estimator",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF7B4B2A)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Estimate wood, labor and total project cost",
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(20.dp))

            // HERO CARD

            Card(
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF7B4B2A)
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                )
            ) {

                Column(
                    modifier = Modifier.padding(22.dp)
                ) {

                    Icon(
                        Icons.Default.Calculate,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(40.dp)
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = "Smart Furniture Estimation",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Calculate wood usage, labor, transport and polishing costs instantly.",
                        color = Color(0xFFE7D8CB),
                        lineHeight = 22.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // DIMENSIONS CARD

            Card(
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                )
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            Icons.Default.Straighten,
                            contentDescription = null,
                            tint = Color(0xFFB46A35)
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = "Furniture Dimensions",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = length,
                        onValueChange = {
                            length = it
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text("Length (ft)")
                        },
                        leadingIcon = {
                            Icon(Icons.Default.Straighten, null)
                        }
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = width,
                        onValueChange = {
                            width = it
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text("Width (ft)")
                        },
                        leadingIcon = {
                            Icon(Icons.Default.Straighten, null)
                        }
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = height,
                        onValueChange = {
                            height = it
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text("Height (ft)")
                        },
                        leadingIcon = {
                            Icon(Icons.Default.Straighten, null)
                        }
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = quantity,
                        onValueChange = {
                            quantity = it
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text("Quantity")
                        },
                        leadingIcon = {
                            Icon(Icons.Default.Calculate, null)
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // WOOD TYPE

            Card(
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                )
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            Icons.Default.Forest,
                            contentDescription = null,
                            tint = Color(0xFFB46A35)
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = "Wood Material",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = {
                            expanded = !expanded
                        }
                    ) {

                        OutlinedTextField(
                            value = selectedWood,
                            onValueChange = {},
                            readOnly = true,
                            modifier = Modifier
                                .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                                .fillMaxWidth(),
                            label = {
                                Text("Select Wood Type")
                            },
                            leadingIcon = {
                                Icon(Icons.Default.Forest, null)
                            }
                        )

                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = {
                                expanded = false
                            }
                        ) {

                            woodTypes.forEach { wood ->

                                DropdownMenuItem(
                                    text = {
                                        Text(wood)
                                    },
                                    onClick = {

                                        selectedWood = wood
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // ADDITIONAL COSTS

            Card(
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                )
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            Icons.Default.AttachMoney,
                            contentDescription = null,
                            tint = Color(0xFFB46A35)
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = "Additional Costs",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = laborCost,
                        onValueChange = {
                            laborCost = it
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text("Labor Cost (₹)")
                        },
                        leadingIcon = {
                            Icon(Icons.Default.Engineering, null)
                        }
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = polishCost,
                        onValueChange = {
                            polishCost = it
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text("Polish / Paint Cost (₹)")
                        },
                        leadingIcon = {
                            Icon(Icons.Default.FormatPaint, null)
                        }
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = transportCost,
                        onValueChange = {
                            transportCost = it
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text("Transport Cost (₹)")
                        },
                        leadingIcon = {
                            Icon(Icons.Default.LocalShipping, null)
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // RESULT CARD

            Card(
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFFFF8F1)
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                )
            ) {

                Column(
                    modifier = Modifier.padding(20.dp)
                ) {

                    Text(
                        text = "Estimated Summary",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    ResultRow(
                        label = "Wood Type",
                        value = selectedWood
                    )

                    ResultRow(
                        label = "Volume",
                        value = "%.2f cubic ft".format(volume)
                    )

                    ResultRow(
                        label = "Wood Cost",
                        value = "₹ %.2f".format(woodCost)
                    )

                    ResultRow(
                        label = "Labor Cost",
                        value = "₹ ${laborCost.ifEmpty { "0" }}"
                    )

                    ResultRow(
                        label = "Polish Cost",
                        value = "₹ ${polishCost.ifEmpty { "0" }}"
                    )

                    ResultRow(
                        label = "Transport Cost",
                        value = "₹ ${transportCost.ifEmpty { "0" }}"
                    )

                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 14.dp)
                    )
                    ResultRow(
                        label = "GST (18%)",
                        value = "₹ %.2f".format(gst)
                    )

                    ResultRow(
                        label = "TOTAL COST",
                        value = "₹ %.2f".format(totalCost),
                        isTotal = true
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = {

                            sharedViewModel.updateAmount(
                                "%.2f".format(totalCost)
                            )

                            onNavigateToQuote()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFB46A35)
                        )
                    ) {

                        Icon(
                            Icons.Default.Calculate,
                            contentDescription = null
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = "Generate Quotation",
                            fontSize = 16.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

@Composable
fun ResultRow(
    label: String,
    value: String,
    isTotal: Boolean = false
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = label,
            fontWeight = if (isTotal) FontWeight.Bold else FontWeight.Medium,
            fontSize = if (isTotal) 18.sp else 16.sp
        )

        Text(
            text = value,
            fontWeight = if (isTotal) FontWeight.Bold else FontWeight.SemiBold,
            color = if (isTotal) Color(0xFF7B4B2A) else Color.Black,
            fontSize = if (isTotal) 18.sp else 16.sp
        )
    }

    Spacer(modifier = Modifier.height(10.dp))
}
