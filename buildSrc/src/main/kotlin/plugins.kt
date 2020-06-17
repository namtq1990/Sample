import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BuildType
import com.crashlytics.tools.gradle.CrashlyticsExtension
import com.github.triplet.gradle.play.PlayPublisherExtension
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.plugins.ExtensionContainer
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.support.delegates.ProjectDelegate
import java.io.File

@Suppress("UNCHECKED_CAST")
internal fun <T> ProjectDelegate.typedProperty(name: String): T? = findProperty(name) as T?

internal fun ExtensionContainer.android(block: BaseExtension.() -> Unit) {
    findByType(BaseExtension::class.java)?.apply(block)
}

internal fun ExtensionContainer.androidApp(block: AppExtension.() -> Unit) {
    findByType(AppExtension::class.java)?.apply(block)
}

internal fun ExtensionContainer.androidLibrary(block: LibraryExtension.() -> Unit) {
    findByType(LibraryExtension::class.java)!!.apply(block)
}

internal fun ExtensionContainer.crashlytics(block: CrashlyticsExtension.() -> Unit) {
    findByType(CrashlyticsExtension::class.java)?.apply(block)
}

internal fun ExtensionContainer.play(block: PlayPublisherExtension.() -> Unit) {
    findByType(PlayPublisherExtension::class.java)?.apply(block)
}

internal fun ExtensionContainer.playConfigs(block: NamedDomainObjectContainer<PlayPublisherExtension>.() -> Unit) {
    @Suppress("UNCHECKED_CAST")
    (findByName("playConfigs") as? NamedDomainObjectContainer<PlayPublisherExtension>)?.apply(block)
}

@Suppress("UnstableApiUsage")
internal fun ExtensionContainer.java(block: JavaPluginExtension.() -> Unit) {
    findByType(JavaPluginExtension::class.java)?.apply(block)
}

var LibraryExtension.generateBuildConfig: Boolean
    get() = throw UnsupportedOperationException("Property is used for setting only")
    set(value) {
        libraryVariants.all {
            generateBuildConfigProvider.configure { enabled = value }
        }
    }

fun BaseExtension.defaultFile(proguardFile: String): File = getDefaultProguardFile(proguardFile)

fun BuildType.enableMinify(vararg files: Any) {
    isMinifyEnabled = true
    proguardFiles(*files)
}
