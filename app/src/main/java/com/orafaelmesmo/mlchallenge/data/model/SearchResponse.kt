package com.orafaelmesmo.mlchallenge.data.model

import com.squareup.moshi.Json

data class SearchResponse(
    @Json(name = "site_id")
    val siteId: String,
    @Json(name = "country_default_time_zone")
    val countryDefaultTimeZone: String,
    val query: String,
    val paging: Paging,
    val results: List<ProductRemote>,
)

