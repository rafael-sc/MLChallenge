package com.orafaelmesmo.mlchallenge.domain.usecase

import androidx.paging.PagingData
import com.orafaelmesmo.mlchallenge.commom.AppLogger
import com.orafaelmesmo.mlchallenge.data.remote.ProductApi
import com.orafaelmesmo.mlchallenge.domain.model.Product
import com.orafaelmesmo.mlchallenge.domain.repository.ProductRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchProductsUseCaseImplTest {
    @MockK(relaxed = true)
    private lateinit var appLogger: AppLogger

    @MockK(relaxed = true)
    private lateinit var repository: ProductRepository

    @MockK(relaxed = true)
    private lateinit var apiService: ProductApi

    private lateinit var useCase: SearchProductsUseCaseImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = SearchProductsUseCaseImpl(repository, appLogger)
    }

    @Test
    fun `searchProducts should return paging data when repository returns results`() =
        runTest {
            // Arrange
            val query = "iphone"
            val expectedProducts =
                listOf(
                    Product("1", "iPhone 13", "https://example.com/iphone13.jpg", "R$ 5000"),
                    Product("2", "iPhone 14", "https://example.com/iphone14.jpg", "R$ 6000"),
                )

            val expectedPagingData = PagingData.from(expectedProducts)

            coEvery { (repository.searchProducts(query)) } returns (flowOf(expectedPagingData))

            // Act
            val result = useCase.searchProducts(query).first()

            // Assert
            assertEquals(expectedPagingData, result)
            coVerify(exactly = 1) { repository.searchProducts(query) }
        }

    @Test
    fun `searchProducts should throw exception when API call fails`() =
        runBlocking {
            // Arrange
            val query = "iphone"
            val exceptionMessage = "Expected at least one element"
            coEvery { apiService.searchProducts(query, any(), any()) } throws RuntimeException(exceptionMessage)

            // Act & Assert
            val exception =
                assertThrows(RuntimeException::class.java) {
                    runBlocking {
                        repository.searchProducts(query).first()
                    }
                }

            assertEquals(exceptionMessage, exception.message)
        }
}
