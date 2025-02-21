package com.orafaelmesmo.mlchallenge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.orafaelmesmo.mlchallenge.domain.model.Product
import com.orafaelmesmo.mlchallenge.domain.usecase.SearchProductsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf

class SearchViewModel(
    private val searchProductsUseCase: SearchProductsUseCase,
) : ViewModel() {
    private val _searchState = MutableStateFlow<SearchState>(SearchState.Idle)
    val searchState: StateFlow<SearchState> get() = _searchState

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> get() = _searchQuery

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val productsPagingData: Flow<PagingData<Product>> = _searchQuery
        .debounce(500)
        .distinctUntilChanged()
        .flatMapLatest { currentQuery ->
            if (currentQuery.isNotEmpty()) {
                searchProductsUseCase.searchProducts(currentQuery)
                    .cachedIn(viewModelScope)
            } else {
                flowOf(PagingData.empty())
            }
        }

    fun searchProducts(query: String) {
        _searchQuery.value = query
    }
}
