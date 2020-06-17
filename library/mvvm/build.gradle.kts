plugins {
    id("com.android.library")
    kotlin("android")
}

apply(plugin = "module-android-library")
apply(plugin = "config-coroutines")

dependencies {
    implementation(project(deps.library.logger))
    implementation(project(deps.library.text))
    api(deps.androidx.activity)
    api(deps.androidx.constraintlayout)
    api(deps.androidx.core)
    api(deps.androidx.fragment)
    api(deps.androidx.lifecycle.runtime)
    api(deps.androidx.lifecycle.viewmodel)
    api(deps.androidx.material)
    api(deps.androidx.recyclerview)
    api(deps.androidx.viewpager)
    api(deps.coroutines.binding.appcompat)
    api(deps.coroutines.binding.material)
}
