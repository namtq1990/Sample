import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += listOf(
            "-Xuse-experimental=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-Xuse-experimental=kotlinx.coroutines.FlowPreview"
        )
    }
}

dependencies {
    "implementation"(project(deps.library.coroutines))
    "implementation"(deps.coroutines.android)
    "testImplementation"(project(deps.library.coroutines_test))
    "testImplementation"(deps.coroutines.test)
}
