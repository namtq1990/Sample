

plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android-extensions")
}

apply(plugin= "module-android-app")

android {
//    buildToolsVersion "29.0.0"

    defaultConfig {
        applicationId = config.android.app.id.base
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            applicationIdSuffix = config.android.app.suffix.debug
//            enableLogging(true)
        }
//        getByName("staging") {
//            applicationIdSuffix = config.android.app.suffix.staging
//        }
        getByName("release") {
            applicationIdSuffix = config.android.app.suffix.release
            isMinifyEnabled = false
//            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }

}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(deps.kotlin.stdlib)
//    implementation(deps.androidx.appcompat)
//    implementation(deps.kotlin.core)
//    testImplementation (deps.test.junit)

    implementation(project(deps.feature.splash))
    implementation(project(deps.library.activity))
    implementation(project(deps.library.text))
    implementation(project(deps.library.logger))

    implementation(deps.androidx.navigation.fragment)
    implementation(deps.androidx.navigation.ui)

    implementation(deps.koin.android)

//    androidTestImplementation(deps.androidx.junit)
//    androidTestImplementation(deps.androidx.expresso)
}
