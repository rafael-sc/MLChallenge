package com.orafaelmesmo.mlchallenge.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ProductRemote(
    val id: String,
    val title: String,
    val price: Double,
    val thumbnail: String,
)
