package com.orafaelmesmo.mlchallenge.presentation.viewmodel

import com.orafaelmesmo.mlchallenge.domain.model.Product

sealed class ScreenState {
    object Idle : ScreenState()

    object Loading : ScreenState()

    data class Success(val products: List<Product>) : ScreenState()

    data class Error(val message: String) : ScreenState()
}
