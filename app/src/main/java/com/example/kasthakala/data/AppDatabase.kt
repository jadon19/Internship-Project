package com.example.kasthakala.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kasthakala.favorites.FavoriteEntity
import com.example.kasthakala.portfolio.PortfolioEntity
import com.example.kasthakala.quotation.QuoteEntity

@Database(
    entities = [FavoriteEntity::class, PortfolioEntity::class, QuoteEntity::class],
    version = 3,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
    abstract fun portfolioDao(): PortfolioDao
    abstract fun quoteDao(): QuoteDao
}