package com.orafaelmesmo.mlchallenge.domain.usecase

import com.orafaelmesmo.mlchallenge.domain.model.ProductDetail
import com.orafaelmesmo.mlchallenge.domain.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class ProductsDetailsUseCaseImplTest {
    private val testDispatcher = StandardTestDispatcher()

    @Mock
    private lateinit var repository: ProductRepository

    private lateinit var useCase: ProductsDetailsUseCaseImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        useCase = ProductsDetailsUseCaseImpl(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getProductsDetails should return success when repository call is successful`() =
        runTest {
            // Arrange
            val productId = "MLA123456"
            val productDetail =
                ProductDetail(
                    id = productId,
                    name = "iPhone 12",
                    price = "R$ 5000",
                    images = listOf("https://example.com/image.jpg"),
                    permalink = "https://example.com/product",
                    originalPrice = "R$ 5500",
                    attributes = listOf("Color: Black"),
                    warranty = "1 year",
                    descriptions = "A great phone",
                )
            Mockito.`when`(repository.getProductDetails(productId))
                .thenReturn(Result.success(productDetail))

            // Act
            val result = useCase.getProductsDetails(productId)
            val detail = result.getOrNull()

            assertNotNull(result)
            assertTrue(result.isSuccess)
            assertNotNull(detail)
            assertEquals(productDetail.name, detail?.name)
        }

    @Test
    fun `getProductsDetails should return failure when repository call fails`() =
        runTest {
            // Arrange
            val productId = "MLA123456"
            val exception = Exception("Network error")
            Mockito.`when`(repository.getProductDetails(productId))
                .thenReturn(Result.failure(exception))

            // Act
            val result = useCase.getProductsDetails(productId)

            // Assert
            assertTrue(result.isFailure)
            assertEquals(exception.message, result.exceptionOrNull()?.message)
        }
}
