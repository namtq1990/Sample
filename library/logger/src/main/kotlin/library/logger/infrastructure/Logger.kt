package library.logger.infrastructure

import timber.log.Timber

object Logger {

    fun initialize(isDebug: Boolean, tagPrefix: String) {
        if (isDebug) {
            Timber.plant(TagPrefixDebugTree(tagPrefix))
        }
    }

    private class TagPrefixDebugTree(private val tagPrefix: String) : Timber.DebugTree() {
        override fun log(priority: Int, tag: String?, message: String, throwable: Throwable?) =
            super.log(priority, tagPrefix + tag, message, throwable)
    }
}

fun <T : Any> T.logV(instance: Any) = log(instance) { Timber.v(it) }
fun <T : Any> T.logD(instance: Any) = log(instance) { Timber.d(it) }
fun <T : Any> T.logI(instance: Any) = log(instance) { Timber.i(it) }
fun <T : Any> T.logW(instance: Any) = log(instance) { Timber.w(it) }
fun <T : Any> T.logE(instance: Any) = log(instance) { Timber.e(it) }

private fun <T : Any> T.log(instance: Any, block: (String) -> Unit) {
    Timber.tag(this::class.java.simpleName)
    block(instance.toString())
}
