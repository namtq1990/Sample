package library.coroutines.infrastructure

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

@Suppress("FunctionNaming")
fun ApplicationScope(dispatcher: CoroutineDispatcher = Dispatchers.Main.immediate): CoroutineScope {
    return CoroutineScope(dispatcher).also { applicationScopeHolder += it }
}

// The ApplicationScope() seems to be sometimes a victim of being GC'd. It might be connected with
// similar issue with NonCancellable: https://github.com/Kotlin/kotlinx.coroutines/issues/1061
// The workaround is to keep a reference for every ApplicationScope() created but making all of
// them infinitely referenced though its recommended to make the component that uses it singleton.
private val applicationScopeHolder = mutableSetOf<CoroutineScope>()
