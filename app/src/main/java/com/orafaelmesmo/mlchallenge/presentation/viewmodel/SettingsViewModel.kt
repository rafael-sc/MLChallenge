package com.orafaelmesmo.mlchallenge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orafaelmesmo.mlchallenge.domain.usecase.SettingsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val settingsUseCase: SettingsUseCase
) : ViewModel() {
    val theme: Flow<String> = settingsUseCase.theme
    val textSize: Flow<Float> = settingsUseCase.textSize

    fun saveTheme(theme: String) {
        viewModelScope.launch {
            settingsUseCase.saveTheme(theme)
        }
    }

    fun saveTextSize(size: Float) {
        viewModelScope.launch {
            settingsUseCase.saveTextSize(size)
        }
    }
}
