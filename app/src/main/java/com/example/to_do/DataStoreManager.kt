package com.example.to_do

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

private const val TODO_PREFS = "user_preferences"

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = TODO_PREFS)

enum class SortOrder { NONE, BY_DATE, BY_NAME }

data class UserPreferences(
    val showCompleted: Boolean,
    val sortOrder: SortOrder
)

class DataStoreManager(private val context: Context) {

    private object PreferencesKeys {
        val SORT_ORDER = stringPreferencesKey("sort_order")
        val SHOW_COMPLETED = booleanPreferencesKey("show_completed")
    }

    val userPreferencesFlow: Flow<UserPreferences> = context.dataStore.data.map { preferences ->
        val sortOrder = SortOrder.valueOf(preferences[PreferencesKeys.SORT_ORDER] ?: SortOrder.NONE.name)
        val showCompleted = preferences[PreferencesKeys.SHOW_COMPLETED] ?: false
        UserPreferences(showCompleted, sortOrder)
    }

    suspend fun updateSortOrder(sortOrder: SortOrder) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.SORT_ORDER] = sortOrder.name
        }
    }

    suspend fun updateShowCompleted(showCompleted: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.SHOW_COMPLETED] = showCompleted
        }
    }

    fun fetchInitialPreferences(): UserPreferences = runBlocking {
        val preferences = context.dataStore.data.first()
        val sortOrder = SortOrder.valueOf(preferences[PreferencesKeys.SORT_ORDER] ?: SortOrder.NONE.name)
        val showCompleted = preferences[PreferencesKeys.SHOW_COMPLETED] ?: false
        UserPreferences(showCompleted, sortOrder)
    }
}
