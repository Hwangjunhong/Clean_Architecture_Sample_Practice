package com.hong.base.presentation.util.extension

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.inputmethod.InputMethodManager
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

// 앱이 설치 설치되었는지 판단하는 함수
fun Context.isInstalledApp(packageName: String): Boolean {
    val intent = packageManager.getLaunchIntentForPackage(packageName)
    return intent != null
}

// 특정 앱을 실행하는 함수
fun Context.openApp(packageName: String) {
    val intent = packageManager.getLaunchIntentForPackage(packageName)
    startActivity(intent)
}

// 마켓으로 이동하는 함수
fun Context.market(packageName: String) {
    runCatching {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("market://details?id=$packageName")
        startActivity(intent)
    }.onFailure {
        it.printStackTrace()
    }
}
