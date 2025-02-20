package com.orafaelmesmo.mlchallenge.data.repository

import com.orafaelmesmo.mlchallenge.domain.model.Product

interface ProductRepository {
    suspend fun searchProducts(query: String): List<Product>

    suspend fun getProductDetails(id: String): Product
}
