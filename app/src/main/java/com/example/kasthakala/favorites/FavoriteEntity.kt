package com.example.kasthakala.favorites

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey val designId: String,
    val title: String,
    val imageUrl: String
)