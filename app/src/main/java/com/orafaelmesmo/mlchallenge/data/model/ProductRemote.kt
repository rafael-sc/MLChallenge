package com.orafaelmesmo.mlchallenge.data.model

import com.squareup.moshi.Json

class ProductRemote(
    val id: String,
    val title: String,
    val price: Double,
    val thumbnail: String,
    @Json(name = "original_price") val originalPrice: Double?,
) {
    val priceFormatted: String
        get() = "R$ $price"
}
