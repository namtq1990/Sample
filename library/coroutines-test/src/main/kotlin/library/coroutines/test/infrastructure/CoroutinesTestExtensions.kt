package library.coroutines.test.infrastructure

import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest

fun <T> Flow<T>.lastValue(
    block: suspend TestCoroutineScope.() -> Unit = {}
): T = collectValues(block).last()

fun <T> Flow<T>.collectValues(
    block: suspend TestCoroutineScope.() -> Unit = {}
): List<T> {
    val values = mutableListOf<T>()
    runBlockingTest {
        val job = launch {
            collect { value -> values.add(value) }
        }
        block()
        job.cancel()
    }
    return values.toList()
}

fun <T> ReceiveChannel<T>.collectValues(
    block: suspend TestCoroutineScope.() -> Unit = {}
): List<T> {
    val values = mutableListOf<T>()
    runBlockingTest {
        val job = launch {
            consumeEach { value -> values.add(value) }
        }
        block()
        job.cancel()
    }
    return values.toList()
}
