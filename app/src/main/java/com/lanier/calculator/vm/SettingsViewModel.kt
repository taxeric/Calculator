package com.lanier.calculator.vm

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lanier.calculator.util.updateSentenceShowValue
import kotlinx.coroutines.launch

/**
 * Create by Eric
 * on 2022/8/6
 */
class SettingsViewModel: ViewModel() {

    fun updateShow(context: Context, everyShow: Boolean) {
        viewModelScope.launch {
            updateSentenceShowValue(context, everyShow)
        }
    }
}