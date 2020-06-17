package library.scope.di

import org.koin.core.module.Module
import org.koin.dsl.ScopeSet

fun Module.scope(scope: ScopeControl, scopeSet: ScopeSet.() -> Unit) {
    scope(scope.scopeName, scopeSet)
}
