package com.lanier.calculator.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lanier.calculator.entity.CalculateResult
import com.lanier.calculator.repo.CalculatorDbHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

/**
 * Create by Eric
 * on 2022/8/6
 */
class HistoryViewModel: ViewModel() {

    private val _dbResults = MutableStateFlow<List<CalculateResult>>(emptyList())
    val dbResult get() = _dbResults

    fun getCalculateResults(isImportant: Boolean = false) {
        viewModelScope.launch {
            val list = CalculatorDbHelper.getResults(isImportant)
            _dbResults.tryEmit(list)
        }
    }

    fun update(calculateResult: CalculateResult) {
        viewModelScope.launch {
            CalculatorDbHelper.modify(calculateResult)
        }
    }
}