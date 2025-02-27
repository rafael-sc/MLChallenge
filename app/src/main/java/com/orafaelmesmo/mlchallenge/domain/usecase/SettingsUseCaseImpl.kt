package com.orafaelmesmo.mlchallenge.domain.usecase

import com.orafaelmesmo.mlchallenge.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow

class SettingsUseCaseImpl(
    private val repository: SettingsRepository
) : SettingsUseCase {

    override val theme: Flow<String> = repository.theme
    override val textSize: Flow<Float> = repository.textSize

    override suspend fun saveTheme(theme: String) {
        repository.saveTheme(theme)
    }

    override suspend fun saveTextSize(size: Float) {
        repository.saveTextSize(size)
    }
}