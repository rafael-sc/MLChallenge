package com.orafaelmesmo.mlchallenge.commom

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat

class ResourceProvider(private val context: Context) {
    fun getString(
        resId: Int,
        vararg formatArgs: Any,
    ): String {
        return context.getString(resId, *formatArgs)
    }

    fun getColor(resId: Int): Int {
        return ContextCompat.getColor(context, resId)
    }

    fun getDrawable(resId: Int): Drawable? {
        return ContextCompat.getDrawable(context, resId)
    }

    fun getDimension(resId: Int): Float {
        return context.resources.getDimension(resId)
    }
}
