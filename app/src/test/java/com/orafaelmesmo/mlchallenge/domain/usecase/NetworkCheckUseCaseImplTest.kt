package com.orafaelmesmo.mlchallenge.domain.usecase

import com.orafaelmesmo.mlchallenge.commom.NetworkUtils
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class NetworkCheckUseCaseImplTest {

    private lateinit var useCase: NetworkCheckUseCase

    private val networkUtils: NetworkUtils = mockk(relaxed = true)

    @Before
    fun setUp() {
        useCase = NetworkCheckUseCaseImpl(networkUtils)
    }

    @Test
    fun `isConnected should return true when network is available`() {
        // Arrange
        every { networkUtils.isConnected() } returns true

        // Act
        val result = useCase.isConnected()

        // Assert
        assertTrue(result)
        verify(exactly = 1) { networkUtils.isConnected() }
    }

    @Test
    fun `isConnected should return false when network is unavailable`() {
        every { networkUtils.isConnected() } returns false

        // Act
        val result = useCase.isConnected()

        // Assert
        assertFalse(result)
        verify(exactly = 1) { networkUtils.isConnected() }
    }
}


