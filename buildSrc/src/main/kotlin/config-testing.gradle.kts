dependencies {
    "testImplementation"(project(deps.library.archunit_test))
    "testImplementation"(deps.junit.params)
    "testImplementation"(deps.junit.runtime)
    "testImplementation"(deps.kotlintest.assertions)
    "testImplementation"(deps.mockk.kotlin)
}
