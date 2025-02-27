package com.orafaelmesmo.mlchallenge.data.mapper

import com.orafaelmesmo.mlchallenge.data.model.ProductDescriptionResponse
import org.junit.Assert.assertEquals
import org.junit.Test

class DescriptionMapperTest {
    @Test
    fun toDomain_returnsText_whenTextIsNotEmpty() {
        val response = ProductDescriptionResponse(text = "Description text", plainText = "")
        val result = DescriptionMapper.toDomain(response)
        assertEquals("Description text", result)
    }

    @Test
    fun toDomain_returnsPlainText_whenTextIsEmptyAndPlainTextIsNotEmpty() {
        val response = ProductDescriptionResponse(text = "", plainText = "Plain description text")
        val result = DescriptionMapper.toDomain(response)
        assertEquals("Plain description text", result)
    }

    @Test
    fun toDomain_returnsEmptyString_whenBothTextAndPlainTextAreEmpty() {
        val response = ProductDescriptionResponse(text = "", plainText = "")
        val result = DescriptionMapper.toDomain(response)
        assertEquals("", result)
    }

    @Test
    fun toDomain_returnsEmptyString_whenResponseIsNull() {
        val result = DescriptionMapper.toDomain(null)
        assertEquals("", result)
    }
}
