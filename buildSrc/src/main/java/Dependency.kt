import Versions.ESPRESSO_VERSION
import Versions.FLIPPER_VERSION
import Versions.NAV_VERSION

object Versions {
    const val NAV_VERSION = "2.4.0-alpha10"
    const val ESPRESSO_VERSION = "3.4.0"
    const val FLIPPER_VERSION = "0.125.0"
    const val LIFECYCLE = "2.4.1"
}

object Firebase {
    const val DATABASE = "com.google.firebase:firebase-database-ktx:20.0.5"
    const val FIRESTORE = "com.google.firebase:firebase-firestore-ktx:24.2.0"
}

object Kotlin {
    const val SDK = "org.jetbrains.java:java-stdlib-jdk8:1.5.21"
}

object AndroidX {
    const val MATERIAL = "androidx.compose.material:material:1.1.1"
    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:2.1.4"
    const val APP_COMPAT = "androidx.appcompat:appcompat:1.4.2"
    const val ACTIVITY = "androidx.activity:activity-ktx:1.4.0"
    const val FRAGMENT = "androidx.fragment:fragment-ktx:1.4.1"
    const val DATASTORE = "androidx.datastore:datastore-preferences:1.0.0"
    const val LIFECYCLE_VIEW_MODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE}"
    const val LIFECYCLE_LIVEDATA = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LIFECYCLE}"
    const val LIFECYCLE = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFECYCLE}"
}

object KTX {
    const val CORE = "androidx.core:core-ktx:1.6.0"
}

object Google {
    const val MATERIAL = "com.google.android.material:material:1.6.1"
}

object Test {
    const val JUNIT = "junit:junit:4.+"
    const val ANDROID_JUNIT_RUNNER = "AndroidJUnitRunner"
}

object AndroidTest {
    const val EXT_JUNIT = "androidx.test.ext:junit:1.1.3"
    const val TEST_RUNNER = "androidx.test:runner:1.4.0"
    const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:$ESPRESSO_VERSION"
    const val ESPRESSO_CONTRIB = "androidx.test.espresso:espresso-contrib:$ESPRESSO_VERSION"
    const val ESPRESSO_INTENTS = "androidx.test.espresso:espresso-intents:$ESPRESSO_VERSION"
    const val ESPRESSO_ACCESSIBILITY = "androidx.test.espresso:espresso-accessibility:$ESPRESSO_VERSION"
    const val ESPRESSO_WEB = "androidx.test.espresso:espresso-web:$ESPRESSO_VERSION"
    const val ESPRESSO_IDLING_CONCURRENT = "androidx.test.espresso.idling:idling-concurrent:$ESPRESSO_VERSION"
    const val ESPRESSO_IDLING_RESOURCE = "androidx.test.espresso:espresso-idling-resource:$ESPRESSO_VERSION"
}

object DaggerHilt {
    const val DAGGER_HILT = "com.google.dagger:hilt-android:2.40.5"
    const val DAGGER_HILT_COMPILER = "com.google.dagger:hilt-android-compiler:2.40.5"
    const val DAGGER_HILT_VIEW_MODEL = "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03"
    const val DAGGER_HILT_ANDROIDX_COMPILER = "androidx.hilt:hilt-compiler:1.0.0"
}

object Retrofit {
    const val RETROFIT = "com.squareup.retrofit2:retrofit:2.9.0"
    const val CONVERTER_GSON = "com.squareup.retrofit2:converter-gson:2.9.0"
    const val CONVERTER_JAXB = "com.squareup.retrofit2:converter-jaxb:2.9.0"
    const val CONVERTER_MOSHI = "com.squareup.retrofit2:converter-moshi:2.9.0"
    const val CONVERTER_RX = "com.github.akarnokd:rxjava3-retrofit-adapter:3.0.0"
}

object OkHttp {
    const val OKHTTP = "com.squareup.okhttp3:okhttp:4.9.1"
    const val LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:4.9.1"
}

object Logger {
    const val LOGGER = "com.orhanobut:logger:2.2.0"
}

object Timber {
    const val TIMBER = "com.jakewharton.timber:timber:5.0.1"
}

object Coroutines {
    const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2"
    const val COROUTINES_CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2"
}

object NavComponent {
    const val NAVIGATION_FRAGMENT = "androidx.navigation:navigation-fragment-ktx:$NAV_VERSION"
    const val NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:$NAV_VERSION"
    const val NAVIGATION_DYNAMIC_FEATURES_FRAGMENT = "androidx.navigation:navigation-dynamic-features-fragment:$NAV_VERSION"
    const val NAVIGATION_TESTING = "androidx.navigation:navigation-testing:$NAV_VERSION"
    const val NAVIGATION_COMPOSE = "androidx.navigation:navigation-compose:2.4.0-alpha10"
}

object Rx {
    const val RX_JAVA = "io.reactivex.rxjava3:rxjava:3.1.2"
    const val RX_KOTLIN = "io.reactivex.rxjava3:rxkotlin:3.0.1"
    const val RX_ANDROID = "io.reactivex.rxjava3:rxandroid:3.0.0"
    const val RX_BINDING = "com.jakewharton.rxbinding4:rxbinding:4.0.0"
}

object Glide {
    const val GLIDE = "com.github.bumptech.glide:glide:4.13.2"
    const val GLIDE_COMPILER = "com.github.bumptech.glide:compiler:4.12.0"
}

object Flipper {
    const val FLIPPER = "com.facebook.flipper:flipper:$FLIPPER_VERSION"
    const val FLIPPER_NETWORK = "com.facebook.flipper:flipper-network-plugin:$FLIPPER_VERSION"
    const val FLIPPER_CANARY = "com.facebook.flipper:flipper-leakcanary2-plugin:$FLIPPER_VERSION"
    const val SOLOADER = "com.facebook.soloader:soloader:0.10.1"
}

object CANARY {
    const val CANARY = "com.squareup.leakcanary:leakcanary-android:2.7"
}

object Jsoup {
    const val JSOUP = "org.jsoup:jsoup:1.13.1"
}