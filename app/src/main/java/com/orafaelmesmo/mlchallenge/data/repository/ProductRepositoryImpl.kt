package com.orafaelmesmo.mlchallenge.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.orafaelmesmo.mlchallenge.R
import com.orafaelmesmo.mlchallenge.commom.ResourceProvider
import com.orafaelmesmo.mlchallenge.data.mapper.DescriptionMapper
import com.orafaelmesmo.mlchallenge.data.mapper.ProductDetailsMapper
import com.orafaelmesmo.mlchallenge.data.remote.ProductApi
import com.orafaelmesmo.mlchallenge.domain.model.Product
import com.orafaelmesmo.mlchallenge.domain.model.ProductDetail
import kotlinx.coroutines.flow.Flow

class ProductRepositoryImpl(
    private val apiService: ProductApi,
    private val resourceProvider: ResourceProvider
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

    override suspend fun getProductDetails(id: String): Result<ProductDetail> {
        return try {
            val response = apiService.getProductDetails(id)
            if (!response.isSuccessful) {
                return Result.failure(
                    Exception(
                        resourceProvider.getString(
                            R.string.error_getting_product_details,
                            response.errorBody()?.string() ?: "Unknown error"
                        )
                    )
                )
            }

            val productDetailsRemote = response.body() ?: return Result.failure(
                Exception("Product not found")
            )

            val description: String = getProductDescription(id)
            Result.success(
                ProductDetailsMapper.toDomain(
                    productDetailsRemote,
                    description
                )
            )
        } catch (e: Exception) {
            Log.e("ProductDetails", "Error getting product details", e)
            Result.failure(e)
        }
    }

    private suspend fun getProductDescription(id: String): String {
        try {
            val descriptions = apiService.getProductDescription(id)
            if (descriptions.isSuccessful) {
                val description = descriptions.body()
                return DescriptionMapper.toDomain(description)
            } else {
                Log.e(
                    "ProductDescription",
                    "Error getting product descriptions: ${
                        descriptions.errorBody()?.string()
                    }",
                )
                return "No description provided"
            }
        } catch (e: Exception) {
            Log.e("ProductDescription", "Error getting product descriptions", e)
            throw e
        }
    }
}
