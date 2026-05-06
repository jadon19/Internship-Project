package com.example.kasthakala.data

import androidx.room.*
import com.example.kasthakala.quotation.QuoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDao {
    @Query("SELECT * FROM quotes")
    fun getAllQuotes(): Flow<List<QuoteEntity>>

    @Insert
    suspend fun insertQuote(quote: QuoteEntity)
}