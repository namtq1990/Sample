import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("kotlinx-serialization")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xuse-experimental=kotlinx.serialization.UnstableDefault"
    }
}

dependencies {
    "implementation"(deps.kotlin.serialization)
}
