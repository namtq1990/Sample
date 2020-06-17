import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

extensions.java {
    sourceCompatibility = config.compiler.java
    targetCompatibility = config.compiler.java
}

extensions.android {
    compileOptions {
        sourceCompatibility = config.compiler.java
        targetCompatibility = config.compiler.java
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = config.compiler.jvm
    }
}

dependencies {
    "implementation"(deps.kotlin.stdlib)
}
