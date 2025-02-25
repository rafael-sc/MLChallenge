package com.orafaelmesmo.mlchallenge.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.orafaelmesmo.mlchallenge.data.mapper.DescriptionMapper
import com.orafaelmesmo.mlchallenge.data.mapper.ProductDetailsMapper
import com.orafaelmesmo.mlchallenge.data.remote.ProductApi
import com.orafaelmesmo.mlchallenge.domain.model.Product
import com.orafaelmesmo.mlchallenge.domain.model.ProductDetail
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

    override suspend fun getProductDetails(id: String): ProductDetail {
        try {
            val response = apiService.getProductDetails(id)
            getProductDescription(id)
            if (response.isSuccessful) {
                val productDetailsRemote = response.body() ?: throw Exception("Product not found")
                val result =
                    ProductDetailsMapper.toDomain(productDetailsRemote, getProductDescription(id))
                return result
            } else {
                throw Exception("Error getting product details: ${response.errorBody()?.string()}")
            }
        } catch (e: Exception) {
            Log.e("ProductDetails", "Error getting product details", e)
            throw e
        }
    }

    private suspend fun getProductDescription(id: String): String {
        try {
            val descriptions = apiService.getProductDescription(id)
            if (descriptions.isSuccessful) {
                val description = descriptions.body()
                return DescriptionMapper.toDomain(description)
            } else {
                throw Exception(
                    "Error getting product descriptions: ${
                        descriptions.errorBody()?.string()
                    }",
                )
            }
        } catch (e: Exception) {
            Log.e("ProductDescription", "Error getting product descriptions", e)
            throw e
        }
    }
}
