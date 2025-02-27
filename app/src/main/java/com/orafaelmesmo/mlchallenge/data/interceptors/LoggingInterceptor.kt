package com.orafaelmesmo.mlchallenge.data.interceptors

import com.orafaelmesmo.mlchallenge.commom.AppLogger
import java.io.IOException
import okhttp3.Interceptor
import okhttp3.Response

class LoggingInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        AppLogger.debug("-- LoggingInterceptor --", "****** Request:\n ${request.url}")
        return chain.proceed(request)
    }
}
