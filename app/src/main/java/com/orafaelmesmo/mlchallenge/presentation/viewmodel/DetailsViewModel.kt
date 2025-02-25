package com.orafaelmesmo.mlchallenge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orafaelmesmo.mlchallenge.domain.model.ProductDetail
import com.orafaelmesmo.mlchallenge.domain.usecase.ProductsDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val productsDetailsUseCase: ProductsDetailsUseCase,
) : ViewModel() {
    private val _searchState = MutableStateFlow<ScreenState>(ScreenState.Idle)
    val searchState: StateFlow<ScreenState> = _searchState

    private val _productDetail = MutableStateFlow<ProductDetail?>(null)
    val productDetail: StateFlow<ProductDetail?> = _productDetail

    fun getProductsDetails(productId: String) {
        if (productDetail.value != null) return
        viewModelScope.launch {
            _searchState.value = ScreenState.Loading
            try {
                productsDetailsUseCase.getProductsDetails(productId).let { detail ->
                    _productDetail.value = detail
                    _searchState.value = ScreenState.Idle
                }
            } catch (e: Exception) {
                _searchState.value = ScreenState.Error(e.message.orEmpty())
            }
        }
    }
}
