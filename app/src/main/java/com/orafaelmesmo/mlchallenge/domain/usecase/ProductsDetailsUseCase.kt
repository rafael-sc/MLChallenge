package com.orafaelmesmo.mlchallenge.domain.usecase

import com.orafaelmesmo.mlchallenge.domain.model.ProductDetail

interface ProductsDetailsUseCase {
    suspend fun getProductsDetails(productId: String): Result<ProductDetail>
}
