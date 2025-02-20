package com.orafaelmesmo.mlchallenge.domain.usecase

import com.orafaelmesmo.mlchallenge.data.repository.ProductRepository
import com.orafaelmesmo.mlchallenge.domain.model.Product

class SearchProductsUseCaseImpl(
    private val repository: ProductRepository,
) : SearchProductsUseCase {
    override suspend fun searchProduct(query: String): Result<List<Product>> {
        return try {
            val products = repository.searchProducts(query)
            Result.success(products)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
