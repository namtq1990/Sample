@file:Suppress("UnstableApiUsage")

plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    mavenCentral()
    google()
    maven("https://maven.fabric.io/public")
    jcenter()
}

dependencies {
    implementation("com.android.tools.build:gradle:3.6.3")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${plugin.kotlin}")
    implementation("org.jetbrains.kotlin:kotlin-serialization:${plugin.kotlin}")
    implementation("com.gradle:build-scan-plugin:2.3")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.1.1")
    implementation("com.github.ben-manes:gradle-versions-plugin:0.22.0")
    implementation("com.github.triplet.gradle:play-publisher:2.2.1")
    implementation("com.google.gms:google-services:4.3.2")
    implementation("io.fabric.tools:gradle:1.31.2")
}

@Suppress("ClassName")
private object plugin {
    const val kotlin = "1.3.50"
}
