package com.orafaelmesmo.mlchallenge.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.orafaelmesmo.mlchallenge.R
import com.orafaelmesmo.mlchallenge.commom.ResourceProvider
import com.orafaelmesmo.mlchallenge.domain.usecase.NetworkCheckUseCase
import com.orafaelmesmo.mlchallenge.domain.usecase.ProductsDetailsUseCase
import com.orafaelmesmo.mlchallenge.presentation.ScreenState
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DetailsViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: DetailsViewModel

    @MockK(relaxed = true)
    private lateinit var productsDetailsUseCase: ProductsDetailsUseCase

    @MockK(relaxed = true)
    private lateinit var networkCheckUseCase: NetworkCheckUseCase

    @MockK(relaxed = true)
    private lateinit var resourceProvider: ResourceProvider

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        MockKAnnotations.init(this)

        every { networkCheckUseCase.isConnected() } returns true
        every { resourceProvider.getString(R.string.error_no_internet_connection) } returns "No Internet Connection"

        viewModel = DetailsViewModel(productsDetailsUseCase, networkCheckUseCase, resourceProvider)
    }

    @Test
    fun `init should set error state when no internet connection`() {
        // Arrange
        every { networkCheckUseCase.isConnected() } returns false

        // Act
        viewModel = DetailsViewModel(productsDetailsUseCase, networkCheckUseCase, resourceProvider)

        // Assert
        assertEquals(ScreenState.Error("No Internet Connection"), viewModel.searchState.value)
    }

    @Test
    fun `getProductsDetails should return error state when no internet connection`() {
        // Arrange
        every { networkCheckUseCase.isConnected() } returns false

        // Act
        viewModel.getProductsDetails("12345")

        // Assert
        assertEquals(
            ScreenState.Error("No Internet Connection"),
            viewModel.searchState.value,
        )
        verify { networkCheckUseCase.isConnected() }
    }
}
