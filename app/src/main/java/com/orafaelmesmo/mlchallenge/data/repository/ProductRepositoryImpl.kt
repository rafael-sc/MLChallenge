package com.orafaelmesmo.mlchallenge.data.repository

import com.orafaelmesmo.mlchallenge.data.mapper.ProductMapper
import com.orafaelmesmo.mlchallenge.data.remote.ProductApi
import com.orafaelmesmo.mlchallenge.domain.model.Product

class ProductRepositoryImpl(
    private val apiService: ProductApi,
) : ProductRepository {
    override suspend fun searchProducts(query: String): List<Product> {
        val response = apiService.searchProducts(query)
        if (response.isSuccessful) {
            val searchResponse = response.body() ?: throw Exception("Empty response")
            return searchResponse.results.map { ProductMapper.toDomain(it) }
        } else {
            throw Exception("Error searching producs: ${response.errorBody()?.string()}")
        }
    }

    override suspend fun getProductDetails(id: String): Product {
        val response = apiService.getProductDetails(id)
        if (response.isSuccessful) {
            val productRemote = response.body() ?: throw Exception("Product not found")
            return ProductMapper.toDomain(productRemote)
        } else {
            throw Exception("Error getting product details: ${response.errorBody()?.string()}")
        }
    }
}
