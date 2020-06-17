// Top-level build file where you can add configuration options common to all sub-projects/modules.
import io.gitlab.arturbosch.detekt.Detekt

plugins {
    id("com.gradle.build-scan")
    id("io.gitlab.arturbosch.detekt")
    id("com.github.ben-manes.versions")
}

apply(plugin = "config-repositories")

buildScan {
    termsOfServiceUrl = "https://gradle.com/terms-of-service"
    termsOfServiceAgree = "yes"
}

detekt {
    input = files("$projectDir")
    config = files("$projectDir/detekt.yml")
    parallel = true
    failFast = false

    reports {
        xml {
            enabled = false
        }
    }
}

dependencies {
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.1.1")
}

tasks.withType<Detekt> {
    exclude("**/*gradle.kts")
    exclude("**/build/**")
    exclude("**/buildSrc/**")
    exclude("resources")
    exclude(".idea")
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
