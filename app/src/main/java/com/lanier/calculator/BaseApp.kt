package com.lanier.calculator

import android.app.Application
import com.lanier.calculator.repo.CalculatorDbHelper

/**
 * Create by Eric
 * on 2022/8/6
 */
class BaseApp: Application() {

    override fun onCreate() {
        super.onCreate()
        CalculatorDbHelper.init(this)
    }
}