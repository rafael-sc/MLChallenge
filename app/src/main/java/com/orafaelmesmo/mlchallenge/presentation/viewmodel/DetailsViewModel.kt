package com.orafaelmesmo.mlchallenge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orafaelmesmo.mlchallenge.R
import com.orafaelmesmo.mlchallenge.commom.ResourceProvider
import com.orafaelmesmo.mlchallenge.domain.model.ProductDetail
import com.orafaelmesmo.mlchallenge.domain.usecase.NetworkCheckUseCase
import com.orafaelmesmo.mlchallenge.domain.usecase.ProductsDetailsUseCase
import com.orafaelmesmo.mlchallenge.presentation.ScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val productsDetailsUseCase: ProductsDetailsUseCase,
    private val networkCheckUseCase: NetworkCheckUseCase,
    private val resourceProvider: ResourceProvider,
) : ViewModel() {
    private val _searchState = MutableStateFlow<ScreenState>(ScreenState.Idle)
    val searchState: StateFlow<ScreenState> = _searchState

    private val _productDetail = MutableStateFlow<ProductDetail?>(null)
    val productDetail: StateFlow<ProductDetail?> = _productDetail

    init {
        if (!networkCheckUseCase.isConnected()) {
            emitErrorState()
        }
    }

    fun getProductsDetails(productId: String) {
        if (!networkCheckUseCase.isConnected()) {
            emitErrorState()
            return
        }

        if (productDetail.value != null) return
        viewModelScope.launch {
            _searchState.value = ScreenState.Loading
            val result = productsDetailsUseCase.getProductsDetails(productId)
            result.onSuccess { detail ->
                _productDetail.value = detail
                _searchState.value = ScreenState.Success(detail)
            }.onFailure { error ->
                _searchState.value = ScreenState.Error(error.message.orEmpty())
            }
        }
    }

    private fun emitErrorState() {
        _searchState.value =
            ScreenState.Error(resourceProvider.getString(R.string.error_no_internet_connection))
    }
}
