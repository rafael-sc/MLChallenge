package com.orafaelmesmo.mlchallenge.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchResponse(
    val paging: Paging,
    val results: List<ProductRemote>,
)
