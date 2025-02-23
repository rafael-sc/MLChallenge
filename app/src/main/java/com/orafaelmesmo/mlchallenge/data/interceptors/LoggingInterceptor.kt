package com.orafaelmesmo.mlchallenge.data.interceptors

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class LoggingInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        Log.d("-- LoggingInterceptor --", "****** Request:\n ${request.url}")
        return chain.proceed(request)
    }
}
