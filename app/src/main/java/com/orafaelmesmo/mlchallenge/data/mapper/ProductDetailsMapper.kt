package com.orafaelmesmo.mlchallenge.data.mapper

import com.orafaelmesmo.mlchallenge.commom.formattedValue
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
                price = price.formattedValue(),
                images = images,
                permalink = permalink,
                originalPrice = originalPrice?.formattedValue().orEmpty(),
                attributes = attributes,
                warranty = warranty.orEmpty(),
                descriptions = description,
            )
        }
    }
}
