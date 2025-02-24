package com.orafaelmesmo.mlchallenge.domain.usecase

import com.orafaelmesmo.mlchallenge.data.repository.ProductRepository
import com.orafaelmesmo.mlchallenge.domain.model.ProductDetail
import kotlinx.coroutines.flow.Flow

class ProductsDetailsUseCaseImpl(
    private val repository: ProductRepository,
) : ProductsDetailsUseCase {
    override suspend fun getProductsDetails(productId: String): Flow<ProductDetail> {
        TODO()
//        return Any()//repository.getProductDetails(productId)
    }
}
