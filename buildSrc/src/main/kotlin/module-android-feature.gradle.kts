apply(plugin = "config-android")
apply(plugin = "config-compiler")
apply(plugin = "config-coroutines")
apply(plugin = "config-linters")
apply(plugin = "config-repositories")
apply(plugin = "config-serialization")
apply(plugin = "config-testing")

extensions.androidLibrary {
    generateBuildConfig = false
}

dependencies {
//    "implementation"(project(deps.common.android))
//    "implementation"(project(deps.common.datetime))
//    "implementation"(project(deps.common.design))
//    "implementation"(project(deps.common.translation))
    "implementation"(project(deps.library.architecture))
    "implementation"(project(deps.library.logger))
    "implementation"(project(deps.library.mvvm))
    "implementation"(project(deps.library.scope))
    "implementation"(project(deps.library.text))
//    "testImplementation"(project(deps.common.datetime_test))
}
