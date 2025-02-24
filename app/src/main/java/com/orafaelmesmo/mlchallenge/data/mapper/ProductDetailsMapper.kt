package com.orafaelmesmo.mlchallenge.data.mapper

import com.orafaelmesmo.mlchallenge.data.model.ProductDetailsResponse
import com.orafaelmesmo.mlchallenge.domain.model.ProductDetail

object ProductDetailsMapper {
    fun toDomain(productDetails: ProductDetailsResponse): ProductDetail {
        return ProductDetail(
            id = productDetails.id,
            name = productDetails.title,
            price = "${productDetails.price}",
//            thumbnail = productDetails.thumbnail.replace("http://", "https://"),
        )
    }
}

data class ProductDetail(
    val id: String,
    val name: String,
    val images: List<String>,
    val permalink: String,
    val price: String,
    val originalPrice: String,

)
