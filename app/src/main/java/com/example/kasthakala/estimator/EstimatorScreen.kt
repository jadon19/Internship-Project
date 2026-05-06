package com.example.kasthakala.estimator

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun EstimatorScreen(onNavigateToQuote: () -> Unit) {
    var l by remember { mutableStateOf("") }
    var w by remember { mutableStateOf("") }
    var h by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<MaterialResult?>(null) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Material Estimator", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = l, onValueChange = { l = it }, label = { Text("Length (ft)") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal))
        OutlinedTextField(value = w, onValueChange = { w = it }, label = { Text("Width (ft)") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal))
        OutlinedTextField(value = h, onValueChange = { h = it }, label = { Text("Height (ft)") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal))

        Button(
            onClick = {
                val length = l.toDoubleOrNull() ?: 0.0
                val width = w.toDoubleOrNull() ?: 0.0
                val height = h.toDoubleOrNull() ?: 0.0
                result = MaterialResult(length * width, length * width * height)
            },
            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
        ) { Text("Calculate") }

        result?.let {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Total Area: ${it.areaSqFt} sq ft")
                    Text("Total Volume: ${it.volumeCuFt} cu ft")
                }
            }
            Button(onClick = onNavigateToQuote, modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
                Text("Generate Quote")
            }
        }
    }
}