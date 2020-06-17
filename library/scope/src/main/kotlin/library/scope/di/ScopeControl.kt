package library.scope.di

import org.koin.core.KoinComponent
import org.koin.core.qualifier.Qualifier

class ScopeControl(internal val scopeName: Qualifier) : KoinComponent {

    private val scopeId = scopeName.toString()

    fun create() = getKoin().createScope(scopeId, scopeName)
    fun close() = getKoin().getScope(scopeId).close()
    fun get() = getKoin().getScope(scopeId)
}
