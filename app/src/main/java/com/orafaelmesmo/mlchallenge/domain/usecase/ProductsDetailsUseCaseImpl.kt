package com.orafaelmesmo.mlchallenge.domain.usecase

import com.orafaelmesmo.mlchallenge.domain.model.ProductDetail
import com.orafaelmesmo.mlchallenge.domain.repository.ProductRepository

class ProductsDetailsUseCaseImpl(
    private val repository: ProductRepository,
) : ProductsDetailsUseCase {
    override suspend fun getProductsDetails(productId: String): Result<ProductDetail> {
        return try {
            repository.getProductDetails(productId)
        } catch (e: Exception) {
            throw e
        }
    }
}
