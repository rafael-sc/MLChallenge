package com.orafaelmesmo.mlchallenge.commom

import android.util.Log

object AppLogger {
    private const val isLoggingEnabled: Boolean = true

    fun debug(
        tag: String,
        message: String,
    ) {
        if (isLoggingEnabled) Log.d(tag, message)
    }

    fun error(
        tag: String,
        message: String,
        throwable: Throwable? = null,
    ) {
        if (isLoggingEnabled) Log.e(tag, message, throwable)
    }

    fun info(
        tag: String,
        message: String,
    ) {
        if (isLoggingEnabled) Log.i(tag, message)
    }

    fun warning(
        tag: String,
        message: String,
    ) {
        if (isLoggingEnabled) Log.w(tag, message)
    }

    fun verbose(
        tag: String,
        message: String,
    ) {
        if (isLoggingEnabled) Log.v(tag, message)
    }
}
