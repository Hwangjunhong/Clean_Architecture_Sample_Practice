package com.hong.base.presentation.util.extension

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import timber.log.Timber


fun Context.toast(message: String?, duration: Int = Toast.LENGTH_SHORT) {
    message?.let {
        Toast.makeText(applicationContext, it, duration).show()
    }
}

fun Context.toast(messageResId: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(applicationContext, resources.getString(messageResId), duration).show()
}

val Context.inputMethodManager
    get() = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

fun Context.isNetworkAvailable(): Boolean {
    val manager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val capabilities = manager.getNetworkCapabilities(manager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                return true
            }
        }
    } else {
        try {
            val activeNetworkInfo = manager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        } catch (e: Exception) {
            Timber.e(e)
        }
    }
    return false
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