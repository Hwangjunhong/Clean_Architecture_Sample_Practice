package com.hong.app

import android.app.Application
import com.hong.cleanarchitecturepractice.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MyApplication : Application() {

    private val timberDebugTree = object : Timber.DebugTree() {
        override fun createStackElementTag(element: StackTraceElement): String? {
            return "${element.fileName}:${element.lineNumber}#${element.methodName}"
        }
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(timberDebugTree)
        }
    }

}