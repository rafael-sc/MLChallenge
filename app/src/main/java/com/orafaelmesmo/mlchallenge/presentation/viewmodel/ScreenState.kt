package com.orafaelmesmo.mlchallenge.presentation.viewmodel

sealed class ScreenState {
    data object Idle : ScreenState()

    data object Loading : ScreenState()

    data class Error(val message: String) : ScreenState()
}
