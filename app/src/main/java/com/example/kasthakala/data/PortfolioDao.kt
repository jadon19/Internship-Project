package com.example.kasthakala.data

import androidx.room.*
import com.example.kasthakala.portfolio.PortfolioEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PortfolioDao {
    @Query("SELECT * FROM portfolio")
    fun getPortfolioItems(): Flow<List<PortfolioEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProject(project: PortfolioEntity)
}
