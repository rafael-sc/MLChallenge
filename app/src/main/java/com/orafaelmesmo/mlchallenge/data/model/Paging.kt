package com.orafaelmesmo.mlchallenge.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Paging(
    val total: Int,
    @Json(name = "primary_results")
    val primaryResults: Int,
    val offset: Int,
    val limit: Int,
)
