apply(plugin = "config-android")
apply(plugin = "config-compiler")
apply(plugin = "config-linters")
apply(plugin = "config-repositories")
apply(plugin = "config-testing")

extensions.androidLibrary {
    generateBuildConfig = false
}
