package com.lanier.calculator.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore

/**
 * Create by Eric
 * on 2022/8/6
 */
const val DATA_STORE_NAME = "local_settings"
const val DATA_STORE_PREFERENCE_SENTENCE = "showSentence"

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(DATA_STORE_NAME)
val showSentence = booleanPreferencesKey(DATA_STORE_PREFERENCE_SENTENCE)

suspend fun updateSentenceShowValue(context: Context, everyShow: Boolean) {
    context.dataStore.edit {
        it[showSentence] = everyShow
    }
}