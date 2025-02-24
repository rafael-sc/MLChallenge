package com.orafaelmesmo.mlchallenge.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductDetailsResponse(
    val id: String,
    val price: Double,
    val title: String,
    @Json(name = "base_price") val basePrice: Double,
    @Json(name = "original_price") val originalPrice: Double,
    @Json(name = "initial_quantity") val initialQuantity: Int,
    @Json(name = "sale_terms") val saleTerms: List<SaleTerm>,
    val permalink: String,
    val thumbnail: String,
    val pictures: List<Picture>,
    @Json(name = "accepts_mercadopago") val acceptsMercadoPago: Boolean,
    val shipping: Shipping,
    val attributes: List<Attribute>,
    val tags: List<String>,
    val warranty: String,
)

@JsonClass(generateAdapter = true)
data class SaleTerm(
    val id: String,
    val name: String,
    @Json(name = "value_id") val valueId: String?,
    @Json(name = "value_name") val valueName: String?,
    @Json(name = "value_struct") val valueStruct: ValueStruct?,
    val values: List<SaleTermValue>,
    @Json(name = "value_type") val valueType: String
)

@JsonClass(generateAdapter = true)
data class SaleTermValue(
    val id: String?,
    val name: String?,
    val struct: ValueStruct?
)

@JsonClass(generateAdapter = true)
data class ValueStruct(
    val number: Double,
    val unit: String
)

@JsonClass(generateAdapter = true)
data class Picture(
    val id: String,
    val url: String,
    @Json(name = "secure_url") val secureUrl: String,
    val size: String,
    @Json(name = "max_size") val maxSize: String,
    val quality: String
)

@JsonClass(generateAdapter = true)
data class Shipping(
    val mode: String,
    val methods: List<Any>,
    val tags: List<String>,
    val dimensions: Any?,
    @Json(name = "local_pick_up") val localPickUp: Boolean,
    @Json(name = "free_shipping") val freeShipping: Boolean,
    @Json(name = "logistic_type") val logisticType: String,
    @Json(name = "store_pick_up") val storePickUp: Boolean
)

@JsonClass(generateAdapter = true)
data class Attribute(
    val id: String,
    val name: String,
    @Json(name = "value_id") val valueId: String?,
    @Json(name = "value_name") val valueName: String?,
    val values: List<AttributeValue>,
    @Json(name = "value_type") val valueType: String
)

@JsonClass(generateAdapter = true)
data class AttributeValue(
    val id: String?,
    val name: String?,
    val struct: ValueStruct?
)
