package com.lanier.calculator.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.lanier.calculator.dao.CalculateDao
import com.lanier.calculator.entity.CalculateResult

/**
 * Create by Eric
 * on 2022/8/6
 */
@Database(entities = [CalculateResult::class], version = 1)
abstract class CalculateDatabase: RoomDatabase() {

    abstract fun obtainDao(): CalculateDao

    companion object {

        private const val DB_NAME = "calculator.db"

        fun createDatabase(context: Context): CalculateDatabase {
            return Room.databaseBuilder(context, CalculateDatabase::class.java, DB_NAME)
                .addCallback(object : RoomDatabase.Callback() {})
                .build()
        }
    }
}