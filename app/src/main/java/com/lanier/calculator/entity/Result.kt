package com.lanier.calculator.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Create by Eric
 * on 2022/8/6
 */
@Entity
data class CalculateResult(
    @PrimaryKey val id: Long,
    @ColumnInfo val important: Boolean = false,
    @ColumnInfo val result: String = "",
    @ColumnInfo val desc: String = ""
)