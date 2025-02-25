package com.orafaelmesmo.mlchallenge.domain.usecase

import com.orafaelmesmo.mlchallenge.data.repository.ProductRepository
import com.orafaelmesmo.mlchallenge.domain.model.ProductDetail

class ProductsDetailsUseCaseImpl(
    private val repository: ProductRepository,
) : ProductsDetailsUseCase {
    override suspend fun getProductsDetails(productId: String): ProductDetail {
        return try {
            repository.getProductDetails(productId)
        } catch (e: Exception) {
            throw e
        }
    }
}
