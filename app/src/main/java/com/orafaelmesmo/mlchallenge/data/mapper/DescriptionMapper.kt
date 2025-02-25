package com.orafaelmesmo.mlchallenge.data.mapper

import com.orafaelmesmo.mlchallenge.data.model.ProductDescriptionResponse

object DescriptionMapper {
    fun toDomain(descriptionResponse: ProductDescriptionResponse?): String {
        return when {
            descriptionResponse?.text?.isNotEmpty() == true -> descriptionResponse.text
            descriptionResponse?.plainText?.isNotEmpty() == true -> descriptionResponse.plainText
            else -> ""
        }
    }
}
