package com.orafaelmesmo.mlchallenge.data.remote

import com.orafaelmesmo.mlchallenge.data.model.ProductDescriptionResponse
import com.orafaelmesmo.mlchallenge.data.model.ProductDetailsResponse
import com.orafaelmesmo.mlchallenge.data.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {
    /**
     https://api.mercadolibre.com/sites/MLB/search?q=iphone
     */
    @GET("sites/MLB/search")
    suspend fun searchProducts(
        @Query("q") query: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ): Response<SearchResponse>

    /**
     https://api.mercadolibre.com/items/MLA123456
     */
    @GET("items/{id}")
    suspend fun getProductDetails(
        @Path("id") id: String,
    ): Response<ProductDetailsResponse>

    /**
     https://api.mercadolibre.com/items/MLA123456/description
     */
    @GET("items/{id}/description")
    suspend fun getProductDescription(
        @Path("id") id: String,
    ): Response<ProductDescriptionResponse>
}
