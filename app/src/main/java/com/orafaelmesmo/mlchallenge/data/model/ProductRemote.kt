package com.orafaelmesmo.mlchallenge.data.model

class ProductRemote(
    val id: String,
    val name: String,
    val price: Double,
    val thumbnail: String
) {
    val priceFormatted: String
        get() = "R$ $price"
}