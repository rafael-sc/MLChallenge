package com.orafaelmesmo.mlchallenge.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ProductRemote(
    val id: String,
    val title: String,
    val price: Double,
    val thumbnail: String,
    @Json(name = "original_price") val originalPrice: Double?,
)
