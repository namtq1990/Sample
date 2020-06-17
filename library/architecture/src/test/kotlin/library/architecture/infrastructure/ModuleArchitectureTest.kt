package library.architecture.infrastructure

import com.tngtech.archunit.junit.AnalyzeClasses
import library.archunit.test.infrastructure.*

@AnalyzeClasses(
    packages = [PACKAGE],
    importOptions = [DoNotIncludeAndroidGeneratedClasses::class]
)
internal class ModuleGlobalArchTest : GlobalArchTest()

@AnalyzeClasses(
    packages = [PACKAGE],
    importOptions = [DoNotIncludeAndroidGeneratedClasses::class, DoNotIncludeTests::class]
)
internal class ModuleLayerArchTest : LayerArchTest()
