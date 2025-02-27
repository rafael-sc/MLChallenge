package com.orafaelmesmo.mlchallenge.domain.repository

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    val theme: Flow<String>
    val textSize: Flow<Float>

    suspend fun saveTheme(theme: String)
    suspend fun saveTextSize(size: Float)
}
