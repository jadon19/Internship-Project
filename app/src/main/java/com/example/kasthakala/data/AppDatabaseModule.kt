package com.example.kasthakala.data

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppDatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "kashtakala_db").build()
    }

    @Provides
    fun provideFavoriteDao(db: AppDatabase) = db.favoriteDao()

    @Provides
    fun providePortfolioDao(db: AppDatabase) = db.portfolioDao()

    @Provides
    fun provideQuoteDao(db: AppDatabase) = db.quoteDao()
}