package com.example.to_do

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val todo_prefs = "user_preferences"

private val Context.dataStore by preferencesDataStore(name = todo_prefs)

class DataStore(private val context: Context) {
    companion object {
        private val TODO_LIST_KEY = stringSetPreferencesKey("todo_list")
        private val TODO_STATUS_KEY = stringPreferencesKey("todo_status")
    }

    val todoList: Flow<List<Pair<String, Boolean>>> = context.dataStore.data.map { preferences ->
        val savedItems = preferences[TODO_LIST_KEY] ?: emptySet()
        val statusMap = preferences[TODO_STATUS_KEY]?.split(",")?.associate {
            val parts = it.split(":")
            parts[0] to (parts[1].toBoolean())
        } ?: emptyMap()
        savedItems.map { task -> task to (statusMap[task] ?: false) }
    }

    suspend fun saveToDoList(items: List<Pair<String, Boolean>>) {
        context.dataStore.edit { preferences ->
            preferences[TODO_LIST_KEY] = items.map { it.first }.toSet()
            preferences[TODO_STATUS_KEY] = items.joinToString(",") { "${it.first}:${it.second}" }
        }
    }
}