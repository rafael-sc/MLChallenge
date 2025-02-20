package com.orafaelmesmo.mlchallenge.domain.model

data class Product(
    val id: String,
    val name: String? = "",
    val price: Double? = 0.0,
    val thumbnail: String? = "",
)
