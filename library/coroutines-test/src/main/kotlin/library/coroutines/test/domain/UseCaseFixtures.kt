package library.coroutines.test.domain

import kotlinx.coroutines.test.TestCoroutineScope
import library.coroutines.domain.CoroutineScopeUseCase
import library.coroutines.domain.invoke

fun testScope(): TestCoroutineScope = TestCoroutineScope()

operator fun <I, O> CoroutineScopeUseCase<I, O>.invoke(input: I): O = invoke(testScope(), input)
operator fun <O> CoroutineScopeUseCase<Unit, O>.invoke(): O = invoke(testScope())
