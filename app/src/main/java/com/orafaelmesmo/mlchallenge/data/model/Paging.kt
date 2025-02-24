package com.orafaelmesmo.mlchallenge.data.model

import com.squareup.moshi.Json

data class Paging(
    val total: Int,
    @Json(name = "primary_results")
    val primaryResults: Int,
    val offset: Int,
    val limit: Int,
)
