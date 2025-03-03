package com.orafaelmesmo.mlchallenge.domain.usecase

import androidx.paging.PagingData
import com.orafaelmesmo.mlchallenge.commom.AppLogger
import com.orafaelmesmo.mlchallenge.domain.model.Product
import com.orafaelmesmo.mlchallenge.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

class SearchProductsUseCaseImpl(
    private val repository: ProductRepository,
    private val appLogger: AppLogger,
) : SearchProductsUseCase {
    override suspend fun searchProducts(query: String): Flow<PagingData<Product>> {
        val result = repository.searchProducts(query)
        return result
            .catch { exception ->
                appLogger.error("SearchProductsUseCase", "Erro ao buscar produtos", exception)
                emit(PagingData.empty())
            }
    }
}
