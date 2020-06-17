package library.coroutines.domain

import kotlinx.coroutines.CoroutineScope

interface CoroutineScopeUseCase<in I, out O> {
    operator fun invoke(coroutineScope: CoroutineScope, input: I): O
}

operator fun <O> CoroutineScopeUseCase<Unit, O>.invoke(coroutineScope: CoroutineScope): O {
    return invoke(coroutineScope, Unit)
}
