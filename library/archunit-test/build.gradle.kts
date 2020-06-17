plugins {
    kotlin("jvm")
}

apply(plugin = "module-jvm-library")

dependencies {
    implementation(project(deps.library.architecture))
    implementation(project(deps.library.coroutines))
    api(deps.archunit)
}
