object deps {

    object androidx {
        const val appcompat = "androidx.appcompat:appcompat:1.1.0"
        const val junit = "androidx.test.ext:junit:1.1.1"
        const val activity = "androidx.activity:activity-ktx:1.0.0"

        const val constraintlayout = "androidx.constraintlayout:constraintlayout:2.0.0-beta3"
        const val core = "androidx.core:core-ktx:1.1.0"
        const val fragment = "androidx.fragment:fragment-ktx:1.1.0"

        object navigation {
            private const val version = "2.1.0"
            const val fragment = "androidx.navigation:navigation-fragment-ktx:$version"
            const val ui = "androidx.navigation:navigation-ui-ktx:$version"
        }

        object lifecycle {
            private const val version = "2.2.0-rc01"
            const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
            const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val service = "androidx.lifecycle:lifecycle-service:$version"
        }

        const val material = "com.google.android.material:material:1.1.0-beta01"
        const val recyclerview = "androidx.recyclerview:recyclerview:1.1.0-rc01"
        const val viewpager = "androidx.viewpager2:viewpager2:1.0.0-rc01"
    }

    object kotlin {
        private const val version = "1.3.50"

        const val core = "androidx.core:core-ktx:1.1.0"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-runtime:0.13.0"
    }

    object test {
        const val junit = "junit:junit:4.12"
    }

    object library {

        private const val module = ":library"
        const val activity = "$module:activity"
        const val architecture = "$module:architecture"
        const val archunit_test = "$module:archunit-test"
        const val contact = "$module:contact"
        const val coroutines = "$module:coroutines"
        const val coroutines_test = "$module:coroutines-test"
        const val depot = "$module:depot"
        const val link = "$module:link"
        const val logger = "$module:logger"
        const val mvvm = "$module:mvvm"
        const val scope = "$module:scope"
        const val text = "$module:text"
    }

    object feature {
        private const val module = ":feature"
        const val splash = "$module:splash"
    }

    const val archunit = "com.tngtech.archunit:archunit-junit4:0.11.0"

    object coroutines {
        private const val version = "1.3.2"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"

        object binding {
            private const val version = "1.2.0"
            const val appcompat = "ru.ldralighieri.corbind:corbind-appcompat:$version"
            const val material = "ru.ldralighieri.corbind:corbind-material:$version"
        }
    }

    object junit {
        const val runtime = "junit:junit:4.13-rc-1"
        const val params = "pl.pragmatists:JUnitParams:1.1.1"
    }

    object mockk {
        private const val version = "1.9.3"
        const val android = "io.mockk:mockk-android:$version"
        const val kotlin = "io.mockk:mockk:$version"
    }

    object firebase {
        const val analytics = "com.google.firebase:firebase-analytics:17.2.1"
        const val crashlytics = "com.crashlytics.sdk.android:crashlytics:2.10.1"
        const val crashlytics_ndk = "com.crashlytics.sdk.android:crashlytics-ndk:2.1.1"
    }

    object kotlintest {
        private const val version = "3.4.2"
        const val assertions = "io.kotlintest:kotlintest-assertions:$version"
    }

    object koin {
        private const val version = "2.0.1"
        const val android = "org.koin:koin-androidx-viewmodel:$version"
        const val test = "org.koin:koin-test:$version"
    }

    const val timber = "com.jakewharton.timber:timber:4.7.1"
}