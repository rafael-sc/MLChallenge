package com.orafaelmesmo.mlchallenge.data.mapper

import com.orafaelmesmo.mlchallenge.data.model.ProductRemote
import org.junit.Assert.assertEquals
import org.junit.Test

class ProductMapperTest {
    @Test
    fun toDomain_mapsCorrectly_whenAllFieldsArePresent() {
        val productRemote =
            ProductRemote(
                id = "1",
                title = "Product Title",
                price = 100.0,
                thumbnail = "http://example.com/image.jpg",
            )
        val result = ProductMapper.toDomain(productRemote)
        assertEquals("1", result.id)
        assertEquals("Product Title", result.name)
        assertEquals("R\$ 100,00", result.price)
        assertEquals("https://example.com/image.jpg", result.thumbnail)
    }

    @Test
    fun toDomain_mapsCorrectly_whenPriceIsZero() {
        val productRemote =
            ProductRemote(
                id = "1",
                title = "Product Title",
                price = 0.0,
                thumbnail = "http://example.com/image.jpg",
            )
        val result = ProductMapper.toDomain(productRemote)
        assertEquals("R\$ 0,00", result.price)
        assertEquals("https://example.com/image.jpg", result.thumbnail)
    }

    @Test
    fun toDomain_mapsCorrectly_whenThumbnailIsHttp() {
        val productRemote =
            ProductRemote(
                id = "1",
                title = "Product Title",
                price = 100.0,
                thumbnail = "http://example.com/image.jpg",
            )
        val result = ProductMapper.toDomain(productRemote)
        assertEquals("https://example.com/image.jpg", result.thumbnail)
        assertEquals("R\$ 100,00", result.price)
        assertEquals("Product Title", result.name)
        assertEquals("1", result.id)
    }
}
