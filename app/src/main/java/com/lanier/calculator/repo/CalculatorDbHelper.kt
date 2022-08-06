package com.lanier.calculator.repo

import android.content.Context
import com.lanier.calculator.db.CalculateDatabase
import com.lanier.calculator.entity.CalculateResult

/**
 * Create by Eric
 * on 2022/8/6
 */
object CalculatorDbHelper {

    private lateinit var database:  CalculateDatabase

    fun init(context: Context){
        database = CalculateDatabase.createDatabase(context)
    }

    suspend fun getResults(important: Boolean = false): List<CalculateResult> {
        return if (important) {
            database.obtainDao().getImportResults()
        } else {
            database.obtainDao().getResults()
        }
    }

    suspend fun getNotImportResult(): List<CalculateResult> {
        return database.obtainDao().getNotImportResults()
    }

    suspend fun modify(calculateResult: CalculateResult){
        database.obtainDao().update(calculateResult)
    }

    suspend fun insert(calculateResult: CalculateResult){
        database.obtainDao().insert(calculateResult)
    }
}