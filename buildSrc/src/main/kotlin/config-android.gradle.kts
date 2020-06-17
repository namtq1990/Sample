extensions.android {
    compileSdkVersion(config.android.app.sdk.compile)

    defaultConfig {
        minSdkVersion(config.android.app.sdk.min)
        targetSdkVersion(config.android.app.sdk.target)
    }

    sourceSets["main"].java.srcDir("src/main/kotlin")
    sourceSets["test"].java.srcDir("src/test/kotlin")
    sourceSets["androidTest"].java.srcDir("src/androidTest/kotlin")
}
