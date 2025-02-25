package com.orafaelmesmo.mlchallenge.commom

import java.net.URL

fun String.convertToHttps(): String {
    return this.replace("http://", "https://")
}

fun String.isValidURL(): Boolean {
    return try {
        URL(this).toURI()
        true
    } catch (e: Exception) {
        false
    }
}
