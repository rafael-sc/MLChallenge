package com.orafaelmesmo.mlchallenge.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PictureResponse(
    val url: String,
    @Json(name = "secure_url")
    val secureUrl: String,
)
