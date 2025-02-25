package com.orafaelmesmo.mlchallenge.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AttributeResponse(
    val id: String,
    val name: String,
    @Json(name = "value_name")
    val valueName: String?,
)
