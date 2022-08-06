package com.lanier.calculator.dao

import androidx.room.*
import com.lanier.calculator.entity.CalculateResult

/**
 * Create by Eric
 * on 2022/8/6
 */
@Dao
interface CalculateDao {

    @Insert
    suspend fun insert(calculateResult: CalculateResult)

    @Update
    suspend fun update(calculateResult: CalculateResult)

    @Query("select * from CalculateResult")
    suspend fun getResults(): List<CalculateResult>

    @Query("select * from CalculateResult where important='true'")
    suspend fun getImportResults(): List<CalculateResult>

    @Query("select * from CalculateResult where important='false'")
    suspend fun getNotImportResults(): List<CalculateResult>
}