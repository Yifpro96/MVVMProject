package com.aoxing.mymvvm

/**
 * <pre>
 *     author: dhl
 *     date  : 2020/7/3
 *     desc  : 如果数量少的话，放在一个类里面就可以，如果数量多的话，可以拆分为多个类
 * </pre>
 */

object Versions {
    const val retrofit = "2.9.0"
    const val okhttpLogging = "4.9.0"
    const val appcompat = "1.2.0"
    const val coreKtx = "1.3.2"
    const val constraintlayout = "2.0.4"
    const val paging = "3.0.0-alpha11"
    const val timber = "4.7.1"
    const val kotlin = "1.4.10"
    const val kotlinCoroutinesCore = "1.3.7"
    const val kotlinCoroutinesAndrid = "1.3.6"
    const val koin = "2.2.2"
    const val work = "2.2.0"
    const val room = "2.3.0-alpha01"
    const val cardview = "1.0.0"
    const val recyclerview = "1.0.0"
    const val fragment = "1.3.0-alpha06"
    const val anko = "0.10.8"
    const val swiperefreshlayout = "1.1.0"
    const val junit = "4.+"
    const val junitExt = "1.1.2"
    const val espressoCore = "3.3.0"
    const val jDatabinding = "1.0.4"
    const val progressview = "1.0.2"
    const val runtime = "1.1.0"
    const val hit = "2.28-alpha"
    const val hitViewModule = "1.0.0-alpha01"
    const val appStartup = "1.0.0"
    const val navigation = "2.3.2"
    const val material = "1.2.1"
    const val okhttp = "4.9.0"
    const val autoSize = "1.1.2"
    const val logger = "2.2.0"
    const val banner = "3.0.0"
    const val multidex = "1.0.3"
}

object Room {
    const val runtime = "androidx.room:room-runtime:${Versions.room}"
    const val compiler = "androidx.room:room-compiler:${Versions.room}"
    const val ktx = "androidx.room:room-ktx:${Versions.room}"
    const val rxjava2 = "androidx.room:room-rxjava2:${Versions.room}"
    const val testing = "androidx.room:room-testing:${Versions.room}"
}

object Retrofit {
    const val runtime = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val mock = "com.squareup.retrofit2:retrofit-mock:${Versions.retrofit}"
    const val logging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttpLogging}"
}

object Okhttp {
    const val runtime = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val logging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
}

object Kt {
    const val stdlibJdk7 = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val stdlibJdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutinesCore}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinCoroutinesAndrid}"
    const val test = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
    const val plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
}

object Multidex {
    const val runtime = "com.android.support:multidex:${Versions.multidex}"
}

object Logger {
    const val runtime = "com.orhanobut:logger:${Versions.logger}"
}

object Koin {
    const val core = "org.koin:koin-core:${Versions.koin}"
    const val android = "org.koin:koin-android:${Versions.koin}"
    const val scope = "org.koin:koin-androidx-scope:$${Versions.koin}"
    const val viewmodel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    const val ext = "org.koin:koin-androidx-ext:${Versions.koin}"
}

object Hilt {
    const val daggerRuntime = "com.google.dagger:hilt-android:${Versions.hit}"
    const val daggerCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hit}"
    const val viewModule = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hitViewModule}"
    const val compiler = "androidx.hilt:hilt-compiler:${Versions.hitViewModule}"
}

object Fragment {
    const val runtime = "androidx.fragment:fragment:${Versions.fragment}"
    const val runtimeKtx = "androidx.fragment:fragment-ktx:${Versions.fragment}"
    const val testing = "androidx.fragment:fragment-testing:${Versions.fragment}"
}

object Coil {
    const val runtime = "io.coil-kt:coil:${Versions.runtime}"
}

object Banner {
    const val runtime = "cn.bingoogolapple:bga-banner:${Versions.banner}@aar"
}

object AutoSize {
    const val runtime = "me.jessyan:autosize:${Versions.autoSize}"
}

object Anko {
    const val common = "org.jetbrains.anko:anko-common:${Versions.anko}"
    const val sqlite = "org.jetbrains.anko:anko-sqlite:${Versions.anko}"
    const val coroutines = "org.jetbrains.anko:anko-coroutines:${Versions.anko}"
    const val design = "org.jetbrains.anko:anko-design:${Versions.anko}" // For SnackBars
}

object Depend {
    const val junit = "junit:junit:${Versions.junit}"
    const val androidTestJunit = "androidx.test.ext:junit:${Versions.junitExt}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    const val jDatabinding = "com.hi-dhl:jdatabinding:${Versions.jDatabinding}"
    const val progressview = "com.hi-dhl:progressview:${Versions.progressview}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
}

object AndroidX {
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val constraintlayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
    const val pagingRuntime = "androidx.paging:paging-runtime-ktx:${Versions.paging}"
    const val workRuntime = "androidx.work:work-runtime:${Versions.work}"
    const val workTesting = "androidx.work:work-testing:${Versions.work}"
    const val cardview = "androidx.cardview:cardview:${Versions.cardview}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    const val appStartup = "androidx.startup:startup-runtime:${Versions.appStartup}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val navigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
}

object Android {
    const val material = "com.google.android.material:material:${Versions.material}"
}

