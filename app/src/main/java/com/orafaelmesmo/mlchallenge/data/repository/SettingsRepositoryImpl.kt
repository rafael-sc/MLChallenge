package com.orafaelmesmo.mlchallenge.data.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.orafaelmesmo.mlchallenge.domain.repository.SettingsRepository
import java.io.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "settings")

class SettingsRepositoryImpl(private val context: Context) : SettingsRepository {
    private val THEME_KEY = stringPreferencesKey("theme")
    private val TEXT_SIZE_KEY = floatPreferencesKey("text_size")

    override val theme: Flow<String> =
        context.dataStore.data
            .catch { exception -> if (exception is IOException) emit(emptyPreferences()) else throw exception }
            .map { preferences -> preferences[THEME_KEY] ?: "light" }

    override val textSize: Flow<Float> =
        context.dataStore.data
            .catch { exception -> if (exception is IOException) emit(emptyPreferences()) else throw exception }
            .map { preferences -> preferences[TEXT_SIZE_KEY] ?: 1f }

    override suspend fun saveTheme(theme: String) {
        context.dataStore.edit { preferences -> preferences[THEME_KEY] = theme }
    }

    override suspend fun saveTextSize(size: Float) {
        context.dataStore.edit { preferences -> preferences[TEXT_SIZE_KEY] = size }
    }
}
