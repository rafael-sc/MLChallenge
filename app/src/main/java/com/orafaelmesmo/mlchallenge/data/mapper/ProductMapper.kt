package com.orafaelmesmo.mlchallenge.data.mapper

import com.orafaelmesmo.mlchallenge.commom.convertToHttps
import com.orafaelmesmo.mlchallenge.commom.formattedValue
import com.orafaelmesmo.mlchallenge.data.model.ProductRemote
import com.orafaelmesmo.mlchallenge.domain.model.Product

object ProductMapper {
    fun toDomain(productRemote: ProductRemote): Product {
        return Product(
            id = productRemote.id,
            name = productRemote.title,
            price = productRemote.price.formattedValue(),
            thumbnail = productRemote.thumbnail.convertToHttps(),
        )
    }
}
