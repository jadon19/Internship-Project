package com.example.kasthakala.quotation

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quotes")
data class QuoteEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val customerName: String,

    val customerPhone: String,

    val furnitureName: String,

    val amount: Double,

    val notes: String
)