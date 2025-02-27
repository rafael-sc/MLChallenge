package com.orafaelmesmo.mlchallenge.data.repository

import com.orafaelmesmo.mlchallenge.commom.AppLogger
import com.orafaelmesmo.mlchallenge.commom.ResourceProvider
import com.orafaelmesmo.mlchallenge.data.model.AttributeResponse
import com.orafaelmesmo.mlchallenge.data.model.Paging
import com.orafaelmesmo.mlchallenge.data.model.PictureResponse
import com.orafaelmesmo.mlchallenge.data.model.ProductDescriptionResponse
import com.orafaelmesmo.mlchallenge.data.model.ProductDetailsResponse
import com.orafaelmesmo.mlchallenge.data.model.ProductRemote
import com.orafaelmesmo.mlchallenge.data.model.SearchResponse
import com.orafaelmesmo.mlchallenge.data.remote.ProductApi
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import retrofit2.Response

class ProductRepositoryImplTest {

    private val apiService: ProductApi = mockk()
    private val resourceProvider: ResourceProvider = mockk()
    private val appLogger: AppLogger = mockk()
    private val repository = ProductRepositoryImpl(apiService, resourceProvider, appLogger)

    @Test
    fun `getProductDetails should return success when API call is successful`() = runBlocking {
        // Arrange
        val productId = "MLA123456"
        val productDetailsResponse = ProductDetailsResponse(
            id = productId,
            title = "iPhone 12",
            price = 5000.0,
            pictureList = listOf(PictureResponse("url", "secure_url")),
            permalink = "https://example.com/product",
            originalPrice = 5500.0,
            attributeList = listOf(AttributeResponse("1", "Color", "Black")),
            warranty = "1 year"
        )
        val productDescriptionResponse = ProductDescriptionResponse("Great phone", "Great phone")
        coEvery { apiService.getProductDetails(productId) } returns Response.success(
            productDetailsResponse
        )
        coEvery { apiService.getProductDescription(productId) } returns Response.success(
            productDescriptionResponse
        )

        // Act
        val result = repository.getProductDetails(productId)

        // Assert
        assertEquals(true, result.isSuccess)
        assertEquals("iPhone 12", result.getOrNull()?.name)
    }

    @Test
    fun `getProductDetails should return failure when API call fails`() = runBlocking {
        // Arrange
        val productId = "MLA123456"
        coEvery { apiService.getProductDetails(productId) } returns Response.error(
            404,
            mockk(relaxed = true)
        )
        coEvery { resourceProvider.getString(any(), any()) } returns "Error message"

        // Act
        val result = repository.getProductDetails(productId)

        // Assert
        assertEquals(true, result.isFailure)
    }

    @Test
    fun `searchProducts should return flow of paging data`() = runBlocking {
        // Arrange
        val query = "iphone"
        val searchResponse = SearchResponse(
            paging = Paging(100, 50, 0, 50),
            results = listOf(ProductRemote("1", "iPhone 12", 5000.0, "thumbnail"))
        )
        coEvery { apiService.searchProducts(query, any(), any()) } returns Response.success(
            searchResponse
        )

        // Act
        val flow = repository.searchProducts(query)
        val pagingData = flow.first()
        // Assert
        assertNotNull(pagingData)
    }
}