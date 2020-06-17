import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
}

apply(plugin = "module-jvm-library")

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xuse-experimental=kotlinx.coroutines.ExperimentalCoroutinesApi"
    }
}

dependencies {
    implementation(deps.coroutines.android)
    testImplementation(project(deps.library.coroutines_test))
    testImplementation(deps.coroutines.test)
}
