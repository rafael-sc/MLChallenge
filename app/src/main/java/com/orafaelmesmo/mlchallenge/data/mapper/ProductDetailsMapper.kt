package com.orafaelmesmo.mlchallenge.data.mapper

import com.orafaelmesmo.mlchallenge.commom.toBrl
import com.orafaelmesmo.mlchallenge.data.model.ProductDetailsResponse
import com.orafaelmesmo.mlchallenge.domain.model.ProductDetail

object ProductDetailsMapper {
    fun toDomain(
        productRemote: ProductDetailsResponse,
        description: String,
    ): ProductDetail {
        with(productRemote) {
            return ProductDetail(
                id = id,
                name = title,
                price = price.toBrl(),
                images = images,
                permalink = permalink,
                originalPrice = originalPrice?.toBrl().orEmpty(),
                attributes = attributes,
                warranty = warranty.orEmpty(),
                descriptions = description,
            )
        }
    }
}
