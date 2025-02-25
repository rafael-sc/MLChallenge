package com.orafaelmesmo.mlchallenge.commom

import android.util.Log


object AppLogger {

    private const val isLoggingEnabled: Boolean = true 

    fun d(tag: String, message: String) {
        if (isLoggingEnabled) Log.d(tag, message)
    }

    fun e(tag: String, message: String, throwable: Throwable? = null) {
        if (isLoggingEnabled) Log.e(tag, message, throwable)
    }

    fun i(tag: String, message: String) {
        if (isLoggingEnabled) Log.i(tag, message)
    }

    fun w(tag: String, message: String) {
        if (isLoggingEnabled) Log.w(tag, message)
    }

    fun v(tag: String, message: String) {
        if (isLoggingEnabled) Log.v(tag, message)
    }

    fun wtf(tag: String, message: String) {
        if (isLoggingEnabled) Log.wtf(tag, message)
    }
}
