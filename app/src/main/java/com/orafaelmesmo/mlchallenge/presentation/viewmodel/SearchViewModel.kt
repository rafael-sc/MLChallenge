package com.orafaelmesmo.mlchallenge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orafaelmesmo.mlchallenge.domain.SearchProductsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class SearchViewModel(
    private val searchProductsUseCase: SearchProductsUseCase
) : ViewModel() {

    private val _searchState = MutableStateFlow<SearchState>(SearchState.Idle)
    val searchState: StateFlow<SearchState> get() = _searchState

    init {
        search("bola de tenis cor de rosa")
    }

    fun search(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _searchState.value = SearchState.Loading
            val result = searchProductsUseCase.searchProduct(query)
            result.fold(
                onSuccess = { products ->
                    _searchState.value = SearchState.Success(products)
                },
                onFailure = { throwable ->
                    _searchState.value = SearchState.Error(throwable.message ?: "Unknown error")
                }
            )
        }
    }
}
