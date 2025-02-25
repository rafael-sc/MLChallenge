package com.orafaelmesmo.mlchallenge.data.model

import com.orafaelmesmo.mlchallenge.commom.convertToHttps
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductDetailsResponse(
    val id: String,
    val title: String,
    val price: Double,
    @Json(name = "pictures")
    val pictureList: List<PictureResponse>,
    val permalink: String,
    @Json(name = "original_price")
    val originalPrice: Double?,
    @Json(name = "attributes")
    val attributeList: List<AttributeResponse>,
    val warranty: String?,
) {
    val images: List<String>
        get() =
            pictureList.map {
                it.secureUrl.ifEmpty { it.url.convertToHttps() }
            }

    val attributes: List<String>
        get() = attributeList.map { "${it.name}: ${it.valueName ?: ""}" }
}
