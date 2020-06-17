package library.scope.di

import org.koin.core.qualifier.named
import kotlin.LazyThreadSafetyMode.SYNCHRONIZED
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

fun inferred(): ReadOnlyProperty<Nothing?, ScopeControl> = ScopeControlProperty()

private class ScopeControlProperty : ReadOnlyProperty<Nothing?, ScopeControl> {
    override fun getValue(thisRef: Nothing?, property: KProperty<*>): ScopeControl {
        return lazy(SYNCHRONIZED) { ScopeControl(named(property.name)) }.value
    }
}
