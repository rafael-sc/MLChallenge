package com.orafaelmesmo.mlchallenge.domain.usecase

import com.orafaelmesmo.mlchallenge.domain.model.Product

interface SearchProductsUseCase {
    suspend fun searchProduct(query: String): Result<List<Product>>
}
