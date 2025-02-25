package com.orafaelmesmo.mlchallenge.commom

import java.util.Locale

fun Double.toBrl() = String.format(Locale.getDefault(), "R\$ %.2f", this)
