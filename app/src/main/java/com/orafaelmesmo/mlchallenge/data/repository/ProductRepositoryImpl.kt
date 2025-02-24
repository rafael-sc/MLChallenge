package com.orafaelmesmo.mlchallenge.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.orafaelmesmo.mlchallenge.data.mapper.ProductDetailsMapper
import com.orafaelmesmo.mlchallenge.data.remote.ProductApi
import com.orafaelmesmo.mlchallenge.domain.model.Product
import kotlinx.coroutines.flow.Flow

class ProductRepositoryImpl(
    private val apiService: ProductApi,
) : ProductRepository {
    override suspend fun searchProducts(query: String): Flow<PagingData<Product>> {
        return Pager(
            PagingConfig(
                pageSize = 50,
                initialLoadSize = 50,
                prefetchDistance = 10,
            ),
        ) {
            ProductPagingSource(apiService, query)
        }.flow
    }

    override suspend fun getProductDetails(id: String): Product {
        val response = apiService.getProductDetails(id)
        if (response.isSuccessful) {
            val productDetailsRemote = response.body() ?: throw Exception("Product not found")
            return ProductDetailsMapper.toDomain(productDetailsRemote)
        } else {
            throw Exception("Error getting product details: ${response.errorBody()?.string()}")
        }
    }
}
