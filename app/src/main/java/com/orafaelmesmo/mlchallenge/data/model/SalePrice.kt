package com.orafaelmesmo.mlchallenge.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SalePrice(
    @Json(name = "price_id") val priceId: String,
    val amount: Double,

    @Json(name = "payment_method_prices") val paymentMethodPrices: List<Any>,
    @Json(name = "payment_method_type") val paymentMethodType: String,
    @Json(name = "regular_amount") val regularAmount: Double?,
    val type: String,
    val metadata: Metadata
)