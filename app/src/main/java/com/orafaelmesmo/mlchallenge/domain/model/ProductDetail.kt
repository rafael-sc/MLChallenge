package com.orafaelmesmo.mlchallenge.domain.model

data class ProductDetail(
    val id: String,
    val name: String,
    val images: List<String>,
    val permalink: String,
    val price: String,
    val originalPrice: String,
    val attributes: List<String>,
    val warranty: String,
    val descriptions: String,
)
