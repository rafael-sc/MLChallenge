package com.orafaelmesmo.mlchallenge.domain.usecase

import androidx.paging.PagingData
import com.orafaelmesmo.mlchallenge.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface SearchProductsUseCase {
    fun searchProducts(query: String): Flow<PagingData<Product>>
}
