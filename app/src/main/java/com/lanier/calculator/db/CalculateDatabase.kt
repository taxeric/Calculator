package com.lanier.calculator.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.lanier.calculator.dao.CalculateDao
import com.lanier.calculator.entity.CalculateResult
import com.lanier.calculator.util.log

/**
 * Create by Eric
 * on 2022/8/6
 */
@Database(entities = [CalculateResult::class], version = 1, exportSchema = false)
abstract class CalculateDatabase: RoomDatabase() {

    abstract fun obtainDao(): CalculateDao

    companion object {

        private const val DB_NAME = "calculator.db"

        fun createDatabase(context: Context, opened: () -> Unit = {}): CalculateDatabase {
            return Room.databaseBuilder(context, CalculateDatabase::class.java, DB_NAME)
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        "create".log()
                    }

                    override fun onOpen(db: SupportSQLiteDatabase) {
                        super.onOpen(db)
                        "open".log()
                        opened()
                    }
                })
                .build()
        }
    }
}