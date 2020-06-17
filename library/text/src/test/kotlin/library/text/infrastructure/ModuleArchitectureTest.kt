package library.text.infrastructure

import com.tngtech.archunit.junit.AnalyzeClasses
import library.archunit.test.infrastructure.DoNotIncludeAndroidGeneratedClasses
import library.archunit.test.infrastructure.DoNotIncludeTests
import library.archunit.test.infrastructure.GlobalArchTest
import library.archunit.test.infrastructure.LayerArchTest
import library.archunit.test.infrastructure.PACKAGE

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
