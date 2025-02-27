package com.orafaelmesmo.mlchallenge.presentation

sealed class ScreenState {
    data object Idle : ScreenState()

    data object Loading : ScreenState()

    data class Success<T>(val data: T) : ScreenState()

    data class Error(val message: String) : ScreenState()
}
