package com.orafaelmesmo.mlchallenge.domain.usecase

import com.orafaelmesmo.mlchallenge.domain.model.ProductDetail
import kotlinx.coroutines.flow.Flow

interface ProductsDetailsUseCase {
    suspend fun getProductsDetails(productId: String): Flow<ProductDetail>
}
