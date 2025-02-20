package com.orafaelmesmo.mlchallenge.presentation.viewmodel

import com.orafaelmesmo.mlchallenge.domain.model.Product

sealed class SearchState {
    object Idle : SearchState()

    object Loading : SearchState()

    data class Success(val products: List<Product>) : SearchState()

    data class Error(val message: String) : SearchState()
}
