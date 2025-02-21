package com.orafaelmesmo.mlchallenge.domain.usecase


import android.util.Log
import androidx.paging.PagingData
import com.orafaelmesmo.mlchallenge.data.repository.ProductRepository
import com.orafaelmesmo.mlchallenge.domain.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch


class SearchProductsUseCaseImpl(
    private val repository: ProductRepository
) : SearchProductsUseCase {

    override fun searchProducts(query: String): Flow<PagingData<Product>> {
        val result  = repository.searchProducts(query)
        return result
            .catch { exception ->
                // Você pode logar o erro, se necessário
                Log.e("SearchProductsUseCase", "Erro ao buscar produtos", exception)
                // Em caso de erro, emite um PagingData vazio
                emit(PagingData.empty())
            }
    }
}

