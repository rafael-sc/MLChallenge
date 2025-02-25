package com.orafaelmesmo.mlchallenge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.orafaelmesmo.mlchallenge.R
import com.orafaelmesmo.mlchallenge.commom.ResourceProvider
import com.orafaelmesmo.mlchallenge.domain.model.Product
import com.orafaelmesmo.mlchallenge.domain.usecase.NetworkCheckUseCase
import com.orafaelmesmo.mlchallenge.domain.usecase.SearchProductsUseCase
import com.orafaelmesmo.mlchallenge.presentation.ScreenState
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

class SearchViewModel(
    private val searchProductsUseCase: SearchProductsUseCase,
    private val networkCheckUseCase: NetworkCheckUseCase,
    private val resourceProvider: ResourceProvider
) : ViewModel() {

    private val searchQuery = MutableStateFlow("")
    private var lastQuery: String = ""

    private val _searchState = MutableStateFlow<ScreenState>(ScreenState.Idle)
    val searchState: StateFlow<ScreenState> = _searchState

    init {
        if (!networkCheckUseCase.isConnected()) {
            emitErrorState()
        }
    }

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
                started = SharingStarted.WhileSubscribed(Long.MAX_VALUE),
                initialValue = PagingData.empty(),
            )

    fun searchProducts(query: String) {
        if (!networkCheckUseCase.isConnected()) {
            emitErrorState()
            return
        }
        searchQuery.value = query
    }

    private fun emitErrorState() {
        _searchState.value =
            ScreenState.Error(resourceProvider.getString(R.string.error_no_internet_connection))
    }

    fun retrySearch() {
        _searchState.value = ScreenState.Idle
        if (!networkCheckUseCase.isConnected()) {
            emitErrorState()
            return
        }
        searchQuery.value = lastQuery
    }

}
