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
    implementation(project(deps.library.architecture))
    implementation(project(deps.library.coroutines))
    implementation(deps.coroutines.test)
    implementation(deps.junit.runtime)
    implementation(deps.mockk.kotlin)
}
