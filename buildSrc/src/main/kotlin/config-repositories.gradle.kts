@file:Suppress("SpellCheckingInspection", "UnstableApiUsage")

repositories {
    mavenCentral()
    google()
    maven {
        url = uri("https://dl.bintray.com/kotlin/kotlinx")
        content {
            includeModule("org.jetbrains.kotlinx", "kotlinx-serialization-runtime")
            includeModule("org.jetbrains.kotlinx", "kotlinx-serialization-runtime-common")
        }
    }
    maven {
        url = uri("https://dl.bintray.com/linkedin/maven")
        content {
            includeGroup("com.linkedin.dexmaker")
        }
    }
    jcenter {
        content {
            includeGroup("org.koin")
            includeGroup("org.jetbrains.trove4j") // Used by lint-gradle
            includeGroup("com.beust")             // Used by detekt
            includeGroup("com.russhwolf")         // Used by multiplatform-settings
        }
    }
}
