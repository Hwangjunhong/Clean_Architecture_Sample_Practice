package com.hong.base.presentation.util.extension

import android.content.Context
import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber

fun View.showKeyboard() {
    context.inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_FORCED)
}

fun View.hideKeyboard(): Boolean {
    return try {
        context.inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    } catch (_: RuntimeException) {
        false
    }
}

fun View.showSnackMessage(
    message: String?,
    anchorView: View? = null,
    backgroundColor: Int,
    textColor: Int,
    length: Int = Snackbar.LENGTH_SHORT
) {
    message?.let {
        try {
            val snack = Snackbar.make(this, it, length)
            snack.setBackgroundTint(ContextCompat.getColor(context, backgroundColor))
            snack.setTextColor(ContextCompat.getColor(context, textColor))
            snack.anchorView = anchorView
            snack.show()
        } catch (ex: Exception) {
            Timber.e(ex)
        }
    }
}

/**
 * DimensionExtension
 */

fun View.startEndPadding(dp: Int = 16) =
    toDp(dp).let { setPadding(it, paddingTop, it, paddingBottom) }

fun View.topBottomPadding(dp: Int = 16) =
    toDp(dp).let { setPadding(paddingLeft, it, paddingRight, it) }

fun View.topPadding(dp: Int = 16) = setPadding(paddingLeft, toDp(dp), paddingRight, paddingBottom)

fun View.bottomPadding(dp: Int = 16) = setPadding(paddingLeft, paddingTop, paddingRight, toDp(dp))

fun View.setPaddingAll(dp: Int = 16) = toDp(dp).let { setPadding(it, it, it, it) }

fun View.removePadding() {
    setPadding(0, 0, 0, 0)
}

fun View.setContainerPadding() {
    val vertical = toDp(8)
    val horizontal = toDp(16)
    setPadding(horizontal, vertical, horizontal, vertical)
}

fun View.toDp(dimension: Int): Int =
    TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dimension.toFloat(),
        resources.displayMetrics
    ).toInt()


/**
 * BackgroundExtension
 */

fun View.setCustomBackground(backgroundOptions: BackgroundOptions) =
    with(backgroundOptions) {
        val drawable = GradientDrawable()
        if (color != null) drawable.setColor(ContextCompat.getColor(context, color))
        if (stroke != null) {
            drawable.setStroke(
                toDp(stroke.size), ContextCompat.getColorStateList(context, stroke.color)
            )
        }
        if (corners != null) {
            val radiusTopLeft = toDp(corners.topLeft).toFloat()
            val radiusTopRight = toDp(corners.topRight).toFloat()
            val radiusBottomRight = toDp(corners.bottomRight).toFloat()
            val radiusBottomLeft = toDp(corners.bottomLeft).toFloat()
            drawable.cornerRadii = floatArrayOf(
                radiusTopLeft,
                radiusTopLeft,
                radiusTopRight,
                radiusTopRight,
                radiusBottomRight,
                radiusBottomRight,
                radiusBottomLeft,
                radiusBottomLeft
            )
        }

        background = drawable
    }

/**
 * Set a rounded corners background for the view with the desired color and border
 * @param color the color resource
 * @param stroke Stroke that contains size and color
 * @param corners the radius value for all the corners in DPs (default: 0dp)
 */
data class BackgroundOptions(
    @ColorRes val color: Int? = null,
    val stroke: Stroke? = null,
    val corners: CornerRadius? = null
)

data class Stroke(
    val size: Int,
    val color: Int
)

data class CornerRadius(
    val topLeft: Int = 0,
    val topRight: Int = 0,
    val bottomRight: Int = 0,
    val bottomLeft: Int = 0
) {
    constructor(radius: Int) : this(
        radius,
        radius,
        radius,
        radius
    )
}