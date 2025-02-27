package com.orafaelmesmo.mlchallenge.domain.usecase

import kotlinx.coroutines.flow.Flow

interface SettingsUseCase {
    val theme: Flow<String>
    val textSize: Flow<Float>

    suspend fun saveTheme(theme: String)
    suspend fun saveTextSize(size: Float)
}
