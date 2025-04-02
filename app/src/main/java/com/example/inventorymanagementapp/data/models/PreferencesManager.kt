package com.example.inventorymanagementapp.data.models

import android.content.Context
import androidx.core.content.edit

class PreferencesManager(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("search_preferences", Context.MODE_PRIVATE)
    private val searchHistoryKey = "search_history"
    private val maxHistorySize = 10

    fun saveSearchQuery(query : String) {
        val history = getSearchHistory().toMutableSet()
        history.add(query)
        if(history.size > maxHistorySize){
            history.remove(history.first())
        }
        sharedPreferences
            .edit() {
                putStringSet(searchHistoryKey, history)
            }
    }

    fun getSearchHistory() : Set<String> {
        return sharedPreferences.getStringSet(searchHistoryKey, emptySet()) ?: emptySet()
    }

    fun clearSearchHistory() {
        sharedPreferences.edit() {
            remove("search_history")
        }
    }
}