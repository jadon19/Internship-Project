package com.example.kasthakala.catalog

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Favorite

import androidx.compose.material3.*

import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DesignDetailScreen(
    title: String,
    price: String,
    imageRes: Int,
    onNavigateToEstimator: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = title,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = price,
            fontSize = 24.sp,
            color = Color(0xFFB46A35),
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text =
                "Modern handcrafted furniture designed " +
                        "for durability, comfort and premium aesthetics.",
            lineHeight = 24.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Recommended Materials",
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text("• Teak Wood")
        Text("• Oak Wood")
        Text("• Premium Plywood")

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = onNavigateToEstimator,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFB46A35)
            )
        ) {

            Icon(Icons.Default.Calculate, null)

            Spacer(modifier = Modifier.width(8.dp))

            Text("Estimate Cost")
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(
            onClick = {

            },
            modifier = Modifier.fillMaxWidth()
        ) {

            Icon(Icons.Default.Favorite, null)

            Spacer(modifier = Modifier.width(8.dp))

            Text("Add To Favorites")
        }
    }
}