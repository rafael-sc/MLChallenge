package com.orafaelmesmo.mlchallenge.data.mapper

import com.orafaelmesmo.mlchallenge.data.model.AttributeResponse
import com.orafaelmesmo.mlchallenge.data.model.PictureResponse
import com.orafaelmesmo.mlchallenge.data.model.ProductDetailsResponse
import org.junit.Assert.assertEquals
import org.junit.Test

class ProductDetailsMapperTest {

    @Test
    fun toDomain_mapsCorrectly_whenAllFieldsArePresent() {
        val response = ProductDetailsResponse(
            id = "1",
            title = "Product Title",
            price = 100.0,
            pictureList = listOf(
                PictureResponse(
                    url = "http:sample.url.com",
                    secureUrl = "https:sample.url.com"
                ),
                PictureResponse(
                    url = "http:sample.url.com",
                    secureUrl = "https:sample.url.com"
                ),
            ),
            permalink = "http://example.com",
            originalPrice = 120.0,
            attributeList = listOf(
                AttributeResponse(
                    id = "1",
                    name = "attr1",
                    valueName = "value1"
                ),
                AttributeResponse(
                    id = "2",
                    name = "attr2",
                    valueName = "value2"
                ),
            ),
            warranty = "1 year"
        )
        val description = "Product description"
        val result = ProductDetailsMapper.toDomain(response, description)
        assertEquals("1", result.id)
        assertEquals("Product Title", result.name)
        assertEquals("R\$ 100,00", result.price)
        assertEquals(listOf("https:sample.url.com", "https:sample.url.com"), result.images)
        assertEquals("http://example.com", result.permalink)
        assertEquals("R\$ 120,00", result.originalPrice)
        assertEquals(listOf("attr1: value1", "attr2: value2"), result.attributes)
        assertEquals("1 year", result.warranty)
        assertEquals("Product description", result.descriptions)
    }

    @Test
    fun toDomain_mapsCorrectly_whenOriginalPriceIsNull() {
        val response = ProductDetailsResponse(
            id = "1",
            title = "Product Title",
            price = 100.0,
            pictureList = listOf(
                PictureResponse(
                    url = "http:sample.url.com",
                    secureUrl = "https:sample.url.com"
                ),
                PictureResponse(
                    url = "http:sample.url.com",
                    secureUrl = "https:sample.url.com"
                ),
            ),
            permalink = "http://example.com",
            originalPrice = null,
            attributeList = listOf(
                AttributeResponse(
                    id = "1",
                    name = "attr1",
                    valueName = "value1"
                ),
                AttributeResponse(
                    id = "2",
                    name = "attr2",
                    valueName = "value2"
                ),
            ),
            warranty = "1 year"
        )
        val description = "Product description"
        val result = ProductDetailsMapper.toDomain(response, description)
        assertEquals("", result.originalPrice)
        assertEquals("R\$ 100,00", result.price)
        assertEquals(listOf("https:sample.url.com", "https:sample.url.com"), result.images)
        assertEquals("http://example.com", result.permalink)
        assertEquals(listOf("attr1: value1", "attr2: value2"), result.attributes)
        assertEquals("1 year", result.warranty)
        assertEquals("Product description", result.descriptions)
    }

    @Test
    fun toDomain_mapsCorrectly_whenWarrantyIsNull() {
        val response = ProductDetailsResponse(
            id = "1",
            title = "Product Title",
            price = 100.0,
            pictureList = listOf(
                PictureResponse(
                    url = "http:sample.url.com",
                    secureUrl = "https:sample.url.com"
                ),
                PictureResponse(
                    url = "http:sample.url.com",
                    secureUrl = "https:sample.url.com"
                ),
            ),
            permalink = "http://example.com",
            originalPrice = 120.0,
            attributeList = listOf(
                AttributeResponse(
                    id = "1",
                    name = "attr1",
                    valueName = "value1"
                ),
                AttributeResponse(
                    id = "2",
                    name = "attr2",
                    valueName = "value2"
                ),
            ),
            warranty = null
        )
        val description = "Product description"
        val result = ProductDetailsMapper.toDomain(response, description)
        assertEquals("", result.warranty)
    }

    @Test
    fun toDomain_mapsCorrectly_whenDescriptionIsEmpty() {
        val response = ProductDetailsResponse(
            id = "1",
            title = "Product Title",
            price = 100.0,
            pictureList = listOf(
                PictureResponse(
                    url = "http:sample.url.com",
                    secureUrl = "https:sample.url.com"
                ),
                PictureResponse(
                    url = "http:sample.url.com",
                    secureUrl = "https:sample.url.com"
                ),
            ),            permalink = "http://example.com",
            originalPrice = 120.0,
            attributeList = listOf(
                AttributeResponse(
                    id = "1",
                    name = "attr1",
                    valueName = "value1"
                ),
                AttributeResponse(
                    id = "2",
                    name = "attr2",
                    valueName = "value2"
                ),
            ),
            warranty = "1 year"
        )
        val description = ""
        val result = ProductDetailsMapper.toDomain(response, description)
        assertEquals("", result.descriptions)
    }
}