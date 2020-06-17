plugins {
    id("com.android.library")
    kotlin("android")
}

apply(plugin = "module-android-library")

dependencies {
    implementation(deps.androidx.lifecycle.runtime)
    implementation(deps.androidx.lifecycle.viewmodel)
    api(deps.koin.android)
}
