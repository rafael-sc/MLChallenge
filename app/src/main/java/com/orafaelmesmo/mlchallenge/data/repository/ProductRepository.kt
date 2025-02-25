package com.orafaelmesmo.mlchallenge.data.repository

import androidx.paging.PagingData
import com.orafaelmesmo.mlchallenge.domain.model.Product
import com.orafaelmesmo.mlchallenge.domain.model.ProductDetail
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun searchProducts(query: String): Flow<PagingData<Product>>

    suspend fun getProductDetails(id: String): ProductDetail
}
