package library.archunit.test.infrastructure

import com.tngtech.archunit.base.DescribedPredicate
import com.tngtech.archunit.core.domain.JavaClass
import com.tngtech.archunit.core.domain.JavaModifier.ABSTRACT
import com.tngtech.archunit.core.importer.ImportOption
import com.tngtech.archunit.core.importer.Location
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.junit.ArchUnitRunner
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses
import com.tngtech.archunit.library.Architectures.layeredArchitecture
import org.junit.runner.RunWith
import library.architecture.domain.UseCase
import library.coroutines.domain.CoUseCase
import library.coroutines.domain.CoroutineScopeUseCase
import java.util.regex.Pattern

const val PACKAGE = "trucker"

@RunWith(ArchUnitRunner::class)
@Suppress("HasPlatformType", "PropertyName", "UnnecessaryAbstractClass", "VariableNaming")
abstract class GlobalArchTest {

    @ArchTest
    val `all classes should reside in one of the specified packages` =
        classes().should().resideInAnyPackage(
            "..di",
            "..system",
            "..here",
            "..data",
            "..presentation",
            "..device",
            "..domain",
            "..model",
            "..infrastructure"
        )

    @ArchTest
    val `layers should have correct dependencies` =
        layeredArchitecture()
            .layer("di").definedBy("..di..")
            .layer("system").definedBy("..system..")
            .layer("here").definedBy("..here..")
            .layer("data").definedBy("..data..")
            .layer("presentation").definedBy("..presentation..")
            .layer("device").definedBy("..device..")
            .layer("domain").definedBy("..domain..")
            .layer("model").definedBy("..model..")
            .layer("infrastructure").definedBy("..infrastructure..")

            // @formatter:off
            .whereLayer("di").mayOnlyBeAccessedByLayers("system")
            .whereLayer("system").mayOnlyBeAccessedByLayers("di")
            .whereLayer("here").mayOnlyBeAccessedByLayers("di", "system")
            .whereLayer("data").mayOnlyBeAccessedByLayers("di", "system", "here")
            .whereLayer("presentation").mayOnlyBeAccessedByLayers("di", "system", "here")
            .whereLayer("device").mayOnlyBeAccessedByLayers("di", "system", "here")
            .whereLayer("domain").mayOnlyBeAccessedByLayers("di", "system", "here", "data", "presentation", "device")
            .whereLayer("model").mayOnlyBeAccessedByLayers("di", "system", "here", "data", "presentation", "device", "domain")
            .whereLayer("infrastructure").mayOnlyBeAccessedByLayers("di", "system", "here", "data", "presentation", "device", "domain", "model")
            // @formatter:on

    @ArchTest
    val `all classes should not use higher tuples` =
        noClasses().should().dependOnClassesThat().haveSimpleName("Triple")
}

@RunWith(ArchUnitRunner::class)
@Suppress("HasPlatformType", "PropertyName", "UnnecessaryAbstractClass", "VariableNaming")
abstract class LayerArchTest {

    @ArchTest
    val `di and system layer classes are only allowed to depend on koin classes` =
        noClasses().that()
            .resideOutsideOfPackages("..di..", "..system..")
            .should().dependOnClassesThat()
            .resideInAPackage("org.koin..")

    @ArchTest
    val `di and presentation and domain layer classes are only allowed to depend on domain use cases` =
        noClasses().that()
            .resideOutsideOfPackages("..di..", "..presentation..", "..domain..")
            .should().dependOnClassesThat()
            .haveNameMatching(".+UseCase.+")

    @ArchTest
    val `repository classes should reside in data or here package` =
        classes().that().haveSimpleNameEndingWith("RepositoryImpl")
            .should().resideInAnyPackage("..here..", "..data..")

    @ArchTest
    val `controller classes should reside in device or here package` =
        classes().that().haveSimpleNameEndingWith("ControllerImpl")
            .should().resideInAnyPackage("..here..", "..device..")

    @ArchTest
    val `presentation and business logic layer classes should not use here sdk imports` =
        noClasses().that()
            .resideInAnyPackage("..presentation..", "..domain..", "..model..", "..infrastructure..")
            .and().doNotHaveFullyQualifiedName("trucker.common.route.model.Route\$Raw")
            .should().dependOnClassesThat().resideInAnyPackage("com.here.android..", "com.nokia..")

    @ArchTest
    val `presentation layer classes should not depend on android platform imports` =
        noClasses().that()
            .resideInAnyPackage("..presentation..")
            .should().dependOnClassesThat()
            .resideInAnyPackage("android..", "androidx..", "com.google..")
            .andShould().notBeAssignableTo("androidx.lifecycle.ViewModel")

    @ArchTest
    val `view model classes should not depend on android platform imports except androidx lifecycle` =
        noClasses().that()
            .areAssignableTo("androidx.lifecycle.ViewModel")
            .should().dependOnClassesThat()
            .resideInAnyPackage(
                "android..", "com.google..", "androidx.activity..", "androidx.annotation..",
                "androidx.appcompat..", "androidx.arch..", "androidx.collection..",
                "androidx.core..", "androidx.fragment..", "androidx.interpolator..",
                "androidx.loader..", "androidx.savedstate..", "androidx.transition..",
                "androidx.vectordrawable..", "androidx.versionedparcelable.."
            )

    @ArchTest
    val `presentation layer should contain only specified classes` =
        classes().that().resideInAPackage("..presentation..").and(areNotNested)
            .should().beInterfaces()
            .orShould().haveSimpleNameEndingWith("ViewModel")
            .orShould().haveSimpleNameEndingWith("Format")
            .orShould().haveSimpleNameEndingWith("State")

    @ArchTest
    val `business logic layer classes should not depend on android imports` =
        noClasses().that()
            .resideInAnyPackage("..domain..", "..model..", "..infrastructure..")
            .should().dependOnClassesThat()
            .resideInAnyPackage("android..", "androidx..", "com.google..")

    @ArchTest
    val `domain layer should contain only specified classes` =
        classes().that().resideInAPackage("..domain..").and(areNotNested)
            .should().haveSimpleNameEndingWith("Repository").andShould().beInterfaces()
            .orShould().haveSimpleNameEndingWith("Controller").andShould().beInterfaces()
            .orShould().haveSimpleNameEndingWith("Navigation").andShould().beInterfaces()
            .orShould().haveSimpleNameEndingWith("UseCase")
            .orShould().haveSimpleNameStartingWith("UseCase")
            .orShould().haveSimpleNameStartingWith("CoUseCase")
            .orShould().haveSimpleNameStartingWith("CoroutineScopeUseCase")

    @ArchTest
    val `domain layer use case wrapper classes should be abstract` =
        classes().that().haveSimpleNameEndingWith("UseCase").should().haveModifier(ABSTRACT)

    @ArchTest
    val `domain layer use case instance classes should not have use case in name` =
        classes().that().areAssignableTo(UseCase::class.java)
            .and().areNotAssignableFrom(UseCase::class.java)
            .or().areAssignableTo(CoUseCase::class.java)
            .and().areNotAssignableFrom(CoUseCase::class.java)
            .or().areAssignableTo(CoroutineScopeUseCase::class.java)
            .and().areNotAssignableFrom(CoroutineScopeUseCase::class.java)
            .should().haveSimpleNameNotEndingWith("UseCase")

    @ArchTest
    val `model layer class names should not contain generic words` =
        noClasses().that().resideInAPackage("..model..")
            .should().haveSimpleNameEndingWith("Info")
            .orShould().haveSimpleNameEndingWith("Data")
            .orShould().haveSimpleNameEndingWith("State")
            .andShould().haveSimpleNameNotStartingWith("State")
}

private val areNotNested = object : DescribedPredicate<JavaClass>("are not nested") {
    override fun apply(input: JavaClass) = !input.name.contains('$')
}

class DoNotIncludeAndroidGeneratedClasses : ImportOption {

    override fun includes(location: Location) = EXCLUDED.none(location::matches)

    private companion object {
        private val R_CLASS = Pattern.compile(".+/R\\.class") // i.e. app/R
        private val R_INNER = Pattern.compile(".+/R\\$.+\\.class") // i.e. app/R$color
        private val BUILD_CONFIG = Pattern.compile(".+/BuildConfig\\.class") // i.e. app/BuildConfig
        private val EXCLUDED = setOf(
            R_CLASS,
            R_INNER,
            BUILD_CONFIG
        )
    }
}

class DoNotIncludeTests : ImportOption {

    override fun includes(location: Location) = EXCLUDED.none(location::matches)

    private companion object {
        private val ANDROID_MODULE = Pattern.compile(".+UnitTest.+\\.class")
        private val JAVA_MODULE = Pattern.compile(".*/build/classes/([^/]+/)?test/.*")
        private val JAVA_TEST_MODULE = Pattern.compile(".*-test/build/.*([^/]+/)?test/.*")
        private val EXCLUDED = setOf(
            ANDROID_MODULE,
            JAVA_MODULE,
            JAVA_TEST_MODULE
        )
    }
}
