package com.example.kasthakala.portfolio

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "portfolio")
data class PortfolioEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val projectTitle: String,

    val description: String,

    val imageUri: String
)