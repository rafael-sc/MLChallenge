package com.orafaelmesmo.mlchallenge.data.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult.Page
import androidx.paging.PagingState
import com.orafaelmesmo.mlchallenge.commom.AppLogger
import com.orafaelmesmo.mlchallenge.data.mapper.ProductMapper
import com.orafaelmesmo.mlchallenge.data.model.PagingResponse
import com.orafaelmesmo.mlchallenge.data.model.ProductRemote
import com.orafaelmesmo.mlchallenge.data.model.SearchResponse
import com.orafaelmesmo.mlchallenge.data.remote.ProductApi
import com.orafaelmesmo.mlchallenge.domain.model.Product
import io.mockk.MockKAnnotations
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.just
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class ProductPagingResponseSourceTest {

    @MockK
    lateinit var productApi: ProductApi

    @MockK(relaxed = true)
    lateinit var appLogger: AppLogger
    private lateinit var pagingSource: ProductPagingSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        pagingSource = ProductPagingSource(productApi, "testQuery", appLogger)
    }

    @Test
    fun `load returns page when response is successful`() = runTest {
        // Mockando resposta da API
        val mockProducts = listOf(
            ProductRemote("1", "Product 1", 10.0, "https://validurl.com/image1.jpg"),
            ProductRemote("2", "Product 2", 20.0, "https://validurl.com/image2.jpg"),
        )
        val pagingResponse = PagingResponse(2, 2, 0, 2)
        val mockResponse = SearchResponse(pagingResponse, mockProducts)

        coEvery { productApi.searchProducts(any(), any(), any()) } returns Response.success(
            mockResponse
        )

        // Executa o carregamento
        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        val expected = Page(
            data = mockProducts.map { ProductMapper.toDomain(it) },
            prevKey = null,
            nextKey = 2
        )
        assertEquals(expected, result)
    }

    @Test
    fun `load returns error when response is unsuccessful`() = runTest {

        val responseBody = mockk<ResponseBody> {
            every { contentType() } returns null
            every { contentLength() } returns 0L
        }
        coEvery { productApi.searchProducts(any(), any(), any()) } returns Response.error(
            400,
            responseBody
        )
        coEvery { appLogger.e(any(), any(), any()) } just Runs

        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        assertTrue(result is PagingSource.LoadResult.Error)
    }

    @Test
    fun `load returns error when API throws exception`() = runTest {
        coEvery {
            productApi.searchProducts(
                any(),
                any(),
                any()
            )
        } throws RuntimeException("Network error")

        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        assertTrue(result is PagingSource.LoadResult.Error)
    }

    @Test
    fun `getRefreshKey returns correct key`() {
        val state = PagingState(
            pages = listOf(
                Page(
                    data = listOf(
                        Product(
                            id = "1",
                            name = "Product 1",
                            price = "10.0",
                            "https://validurl.com/image1.jpg",
                        )
                    ),
                    prevKey = null,
                    nextKey = 2
                )
            ),
            anchorPosition = 0,
            config = PagingConfig(pageSize = 2),
            leadingPlaceholderCount = 0
        )

        val refreshKey = pagingSource.getRefreshKey(state)
        assertEquals(0, refreshKey)
    }
}
