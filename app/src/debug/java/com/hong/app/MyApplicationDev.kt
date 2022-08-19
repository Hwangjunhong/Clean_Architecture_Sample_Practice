package com.hong.app

import android.app.Application
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.crashreporter.CrashReporterPlugin
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.leakcanary2.FlipperLeakListener
import com.facebook.flipper.plugins.leakcanary2.LeakCanary2FlipperPlugin
import com.facebook.flipper.plugins.navigation.NavigationFlipperPlugin
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.soloader.SoLoader
import leakcanary.LeakCanary
import timber.log.Timber

class MyApplicationDev : MyApplication() {

    private val timberDebugTree = object : Timber.DebugTree() {
        override fun createStackElementTag(element: StackTraceElement): String? {
            return "${element.fileName}:${element.lineNumber}#${element.methodName}"
        }
    }

    override fun onCreate() {
        super.onCreate()

        /**
         * set the flipper listener in leak canary config
         */
        LeakCanary.config = LeakCanary.config.copy(
            onHeapAnalyzedListener = FlipperLeakListener()
        )

        SoLoader.init(this, false)

        if (FlipperUtils.shouldEnableFlipper(this)) {
            val client = AndroidFlipperClient.getInstance(this)

            val descriptorMapping = DescriptorMapping.withDefaults()

            client.addPlugin(InspectorFlipperPlugin(this, descriptorMapping))
            client.addPlugin(NetworkFlipperPlugin())
            client.addPlugin(NavigationFlipperPlugin.getInstance())
            client.addPlugin(CrashReporterPlugin.getInstance())
            client.addPlugin(DatabasesFlipperPlugin(this))
            client.addPlugin(LeakCanary2FlipperPlugin())
            client.start()
        }

        Timber.plant(timberDebugTree)

        Timber.d("onCreate: Dev App Start")
    }

}