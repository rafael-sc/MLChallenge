package com.orafaelmesmo.mlchallenge.data.remote

import com.orafaelmesmo.mlchallenge.data.interceptors.LoggingInterceptor
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.mercadolibre.com/"

    // Instância do Moshi para conversão JSON
    private val moshi =
        Moshi.Builder()
            .build()

    private val okHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(LoggingInterceptor())
            .build()

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }
    val apiService: ProductApi = retrofit.create(ProductApi::class.java)
}
