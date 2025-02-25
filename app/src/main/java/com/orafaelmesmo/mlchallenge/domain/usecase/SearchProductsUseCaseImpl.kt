package com.orafaelmesmo.mlchallenge.domain.usecase

import android.util.Log
import androidx.paging.PagingData
import com.orafaelmesmo.mlchallenge.data.repository.ProductRepository
import com.orafaelmesmo.mlchallenge.domain.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

class SearchProductsUseCaseImpl(
    private val repository: ProductRepository,
) : SearchProductsUseCase {
    override suspend fun searchProducts(query: String): Flow<PagingData<Product>> {
        val result = repository.searchProducts(query)
        return result
            .catch { exception ->
                Log.e("SearchProductsUseCase", "Erro ao buscar produtos", exception)
                emit(PagingData.empty())
            }
    }

    override suspend fun getDetails(id: String) {
        repository.getProductDetails(id)
    }
}
