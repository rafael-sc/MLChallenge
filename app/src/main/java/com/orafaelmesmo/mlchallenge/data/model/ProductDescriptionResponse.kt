package com.orafaelmesmo.mlchallenge.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductDescriptionResponse(
    @Json(name = "text")
    val text: String,
    @Json(name = "plain_text")
    val plainText: String,
)
