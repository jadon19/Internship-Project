package com.example.kasthakala.quotation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Save
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kasthakala.shared.SharedEstimatorViewModel
import androidx.compose.material3.*

import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun QuotationScreen(
    viewModel: QuotationViewModel = hiltViewModel()
) {

    val quotations by viewModel
        .quotes
        .collectAsState()

    var customerName by remember {
        mutableStateOf("")
    }

    var customerPhone by remember {
        mutableStateOf("")
    }

    var furnitureName by remember {
        mutableStateOf("")
    }

    val sharedViewModel: SharedEstimatorViewModel =
        viewModel()

    var estimatedCost by remember {

        mutableStateOf(
            sharedViewModel.estimatedAmount.value
        )
    }

    var notes by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Quotation Generator",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF7B4B2A)
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Generate customer furniture quotations",
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(24.dp))

        // FORM CARD

        Card(
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
                        Icons.Default.Description,
                        contentDescription = null,
                        tint = Color(0xFFB46A35)
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = "Create Quotation",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(18.dp))

                OutlinedTextField(
                    value = customerName,
                    onValueChange = {
                        customerName = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text("Customer Name")
                    },
                    leadingIcon = {
                        Icon(Icons.Default.Person, null)
                    }
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = customerPhone,
                    onValueChange = {
                        customerPhone = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text("Customer Phone")
                    },
                    leadingIcon = {
                        Icon(Icons.Default.Phone, null)
                    }
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = furnitureName,
                    onValueChange = {
                        furnitureName = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text("Furniture Name")
                    },
                    leadingIcon = {
                        Icon(Icons.Default.Description, null)
                    }
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = estimatedCost,
                    onValueChange = {
                        estimatedCost = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text("Estimated Cost (₹)")
                    },
                    leadingIcon = {
                        Icon(Icons.Default.Save, null)
                    }
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = notes,
                    onValueChange = {
                        notes = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text("Additional Notes")
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {

                        if (
                            customerName.isNotBlank() &&
                            furnitureName.isNotBlank() &&
                            estimatedCost.isNotBlank()
                        ) {

                            viewModel.addQuote(

                                QuoteEntity(
                                    customerName = customerName,
                                    customerPhone = customerPhone,
                                    furnitureName = furnitureName,
                                    amount = estimatedCost.toDoubleOrNull() ?: 0.0,
                                    notes = notes
                                )
                            )

                            customerName = ""
                            customerPhone = ""
                            furnitureName = ""
                            estimatedCost = ""
                            notes = ""
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFB46A35)
                    )
                ) {

                    Icon(
                        Icons.Default.Save,
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text("Save Quotation")
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Saved Quotations",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (quotations.isEmpty()) {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "No quotations created yet"
                )
            }

        } else {

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {

                items(quotations.size) { index ->

                    QuoteCard(
                        quote = quotations[index]
                    )
                }
            }
        }
    }
}

@Composable
fun QuoteCard(
    quote: QuoteEntity
) {

    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = quote.customerName,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Phone: ${quote.customerPhone}"
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Furniture: ${quote.furnitureName}"
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Estimated Cost: ₹${quote.amount}",
                color = Color(0xFFB46A35),
                fontWeight = FontWeight.Bold
            )

            if (quote.notes.isNotBlank()) {

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Notes: ${quote.notes}"
                )
            }
        }
    }
}
