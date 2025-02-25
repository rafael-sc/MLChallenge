package com.orafaelmesmo.mlchallenge.commom

import java.util.Locale

fun Double.formattedValue() = String.format(Locale.getDefault(), "%.2f", this)
