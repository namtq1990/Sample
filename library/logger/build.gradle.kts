plugins {
    id("com.android.library")
    kotlin("android")
}

apply(plugin = "module-android-library")

dependencies {
    implementation(deps.koin.android)
    implementation(deps.timber)
}
