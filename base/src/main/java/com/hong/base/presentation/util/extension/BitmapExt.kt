package com.hong.base.presentation.util.extension

import android.graphics.Bitmap

fun Bitmap.resizeBitmap(maxResolution: Int = 550): Bitmap? {
    val width = width
    val height = height
    var newWidth = width
    var newHeight = height
    val rate: Float
    if (width > height) {
        if (maxResolution < width) {
            rate = maxResolution / width.toFloat()
            newHeight = (height * rate).toInt()
            newWidth = maxResolution
        }
    } else {
        if (maxResolution < height) {
            rate = maxResolution / height.toFloat()
            newWidth = (width * rate).toInt()
            newHeight = maxResolution
        }
    }
    return Bitmap.createScaledBitmap(this, newWidth, newHeight, true)
}