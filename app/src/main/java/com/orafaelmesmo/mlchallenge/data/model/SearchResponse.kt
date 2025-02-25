package com.orafaelmesmo.mlchallenge.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchResponse(
    val pagingResponse: PagingResponse,
    val results: List<ProductRemote>,
)
