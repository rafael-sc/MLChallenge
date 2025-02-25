package com.orafaelmesmo.mlchallenge.domain.usecase

import androidx.paging.PagingData
import com.orafaelmesmo.mlchallenge.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface SearchProductsUseCase {
    suspend fun searchProducts(query: String): Flow<PagingData<Product>>

    suspend fun getDetails(id: String)
}
