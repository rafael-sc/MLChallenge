package com.orafaelmesmo.mlchallenge.data.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class LoggingInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
//        println("Requested URL: ${request}")
        return chain.proceed(request)
    }
}
