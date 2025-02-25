package com.orafaelmesmo.mlchallenge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.orafaelmesmo.mlchallenge.domain.model.Product
import com.orafaelmesmo.mlchallenge.domain.usecase.SearchProductsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val searchProductsUseCase: SearchProductsUseCase,
) : ViewModel() {
    private val searchState = MutableStateFlow<ScreenState>(ScreenState.Idle)

    private val searchQuery = MutableStateFlow("")
    private var lastQuery: String = ""

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val productsPagingData: StateFlow<PagingData<Product>> =
        searchQuery
            .debounce(500)
            .distinctUntilChanged()
            .flatMapLatest { currentQuery ->
                if (currentQuery.isNotEmpty() && lastQuery != currentQuery) {
                    lastQuery = currentQuery
                    searchProductsUseCase.searchProducts(currentQuery)
                        .cachedIn(viewModelScope)
                } else {
                    flowOf(PagingData.empty())
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = PagingData.empty(),
            )

    fun searchProducts(query: String) {
        searchQuery.value = query
    }

    fun onItemClicked(productId: String) {
        viewModelScope.launch {
            searchProductsUseCase.getDetails("MLB2634583064")
        }
    }
}
