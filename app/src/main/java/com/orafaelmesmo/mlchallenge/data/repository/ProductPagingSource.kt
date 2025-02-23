package com.orafaelmesmo.mlchallenge.data.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.orafaelmesmo.mlchallenge.data.mapper.ProductMapper
import com.orafaelmesmo.mlchallenge.data.model.ProductRemote
import com.orafaelmesmo.mlchallenge.data.remote.ProductApi
import com.orafaelmesmo.mlchallenge.domain.model.Product
import java.net.URL

class ProductPagingSource(
    private val productApi: ProductApi,
    private val query: String,
) : PagingSource<Int, Product>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        val currentOffset = params.key ?: STARTING_OFFSET
        return try {
            val response =
                productApi.searchProducts(query, offset = currentOffset, limit = params.loadSize)

            if (response.isSuccessful) {
                Log.i(TAG, "Product Load successful $response")

                val searchResponse = response.body() ?: throw Exception("Empty response")
                val products =
                    searchResponse.results
                        .filter { isValidProduct(it) }
                        .map { ProductMapper.toDomain(it) }

                val nextKey = if (products.isEmpty()) null else currentOffset + params.loadSize
                LoadResult.Page(
                    data = products,
                    prevKey = if (currentOffset == 0) null else currentOffset - params.loadSize,
                    nextKey = nextKey,
                )
            } else {
                Log.e(TAG, "Empty response body")
                LoadResult.Error(
                    Exception(
                        "Error searching products: ${
                            response.errorBody()?.string()
                        }",
                    ),
                )
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error loading products", e)
            LoadResult.Error(e)
        }
    }

    private fun isValidProduct(productRemote: ProductRemote): Boolean {
        return productRemote.id.isNotEmpty() &&
            productRemote.title.isNotEmpty() &&
            productRemote.price > 0 &&
            isValidUrl(productRemote.thumbnail)
    }

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(state.config.pageSize)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(state.config.pageSize)
        }
    }

    private companion object {
        const val STARTING_OFFSET = 0
        const val TAG = "ProductPagingSource"
    }

    private fun isValidUrl(url: String): Boolean {
        return try {
            URL(url).toURI()
            true
        } catch (e: Exception) {
            false
        }
    }
}
