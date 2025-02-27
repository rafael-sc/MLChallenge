package com.orafaelmesmo.mlchallenge.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import com.orafaelmesmo.mlchallenge.commom.ResourceProvider
import com.orafaelmesmo.mlchallenge.domain.usecase.NetworkCheckUseCase
import com.orafaelmesmo.mlchallenge.domain.usecase.SearchProductsUseCase
import com.orafaelmesmo.mlchallenge.presentation.ScreenState
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: SearchViewModel
    private val searchProductsUseCase: SearchProductsUseCase = mockk(relaxed = true)
    private val networkCheckUseCase: NetworkCheckUseCase = mockk(relaxed = true)
    private val resourceProvider: ResourceProvider = mockk(relaxed = true)

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        every { networkCheckUseCase.isConnected() } returns true
        every { resourceProvider.getString(any()) } returns "No Internet Connection"

        viewModel = SearchViewModel(searchProductsUseCase, networkCheckUseCase, resourceProvider)
    }

    @Test
    fun `init should set error state when no internet connection`() {
        // Arrange
        every { networkCheckUseCase.isConnected() } returns false

        // Act
        viewModel = SearchViewModel(searchProductsUseCase, networkCheckUseCase, resourceProvider)

        // Assert
        assertEquals(ScreenState.Error("No Internet Connection"), viewModel.searchState.value)
    }

    @Test
    fun `searchProducts should update searchQuery when internet is available`() =
        runTest {
            // Arrange
            val query = "iphone"
            coEvery { searchProductsUseCase.searchProducts(query) } returns flowOf(PagingData.empty())

            // Act
            viewModel.searchProducts(query)
            advanceUntilIdle()

            // Assert
            assertNotNull(viewModel.productsPagingData.first())
            verify(exactly = 2) { networkCheckUseCase.isConnected() }
//        coVerify { searchProductsUseCase.searchProducts(query) }
        }

    @Test
    fun `searchProducts should set error state when no internet connection`() {
        // Arrange
        every { networkCheckUseCase.isConnected() } returns false

        // Act
        viewModel.searchProducts("iphone")

        // Assert
        assertEquals(ScreenState.Error("No Internet Connection"), viewModel.searchState.value)
        verify { networkCheckUseCase.isConnected() }
    }

    @Test
    fun `retrySearch should retry last query if internet is available`() =
        runTest {
            // Arrange
            val query = "iphone"
            every { networkCheckUseCase.isConnected() } returns true
            coEvery { searchProductsUseCase.searchProducts(query) } returns flowOf(PagingData.empty())

            viewModel.searchProducts(query)
            advanceUntilIdle()

            // Act
            viewModel.retrySearch()
            advanceUntilIdle()

            // Assert
            assertTrue(viewModel.searchState.value is ScreenState.Idle)
        }

    @Test
    fun `retrySearch should set error state if no internet`() {
        // Arrange
        every { networkCheckUseCase.isConnected() } returns false

        // Act
        viewModel.retrySearch()

        // Assert
        assertEquals(ScreenState.Error("No Internet Connection"), viewModel.searchState.value)
        verify { networkCheckUseCase.isConnected() }
    }
}
