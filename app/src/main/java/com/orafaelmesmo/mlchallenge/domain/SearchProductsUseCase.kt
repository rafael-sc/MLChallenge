package com.orafaelmesmo.mlchallenge.domain


import com.orafaelmesmo.mlchallenge.data.repository.ProductRepository


class SearchProductsUseCase(private val repository: ProductRepository) {

    suspend fun searchProduct(query: String): Result<List<Product>> {
        return try {
            val products = repository.searchProducts(query)
            Result.success(products)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
