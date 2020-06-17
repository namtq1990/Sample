plugins {
    id("com.android.library")
    kotlin("android")
}

apply(plugin = "module-android-library")
apply(plugin = "config-coroutines")

dependencies {
    implementation(project(deps.library.logger))
    implementation(deps.androidx.appcompat)
    implementation(deps.koin.android)
}
