package library.coroutines.test.domain

import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import library.architecture.domain.UseCase
import library.coroutines.domain.CoUseCase

inline fun <reified I : Any, O, reified U : UseCase<I, O>> mockByValue(value: O): U {
    return mockk { every { this@mockk.invoke(any()) } returns value }
}

inline fun <reified I : Any, O, reified U : UseCase<I, Flow<O>>> mockByValues(vararg values: O): U {
    return mockByValue(values.toList().asFlow())
}

inline fun <reified I : Any, O, reified U : UseCase<I, Flow<O>>> mockByFlow(flow: Flow<O>): U {
    return mockByValue(flow)
}

inline fun <reified I : Any, O, reified U : CoUseCase<I, O>> coMockByValue(value: O): U {
    return mockk { coEvery { this@mockk.invoke(any()) } returns value }
}

inline fun <reified I : Any, O, reified U : CoUseCase<I, Flow<O>>> coMockByValues(vararg v: O): U {
    return coMockByValue(v.toList().asFlow())
}

inline fun <reified I : Any, O, reified U : CoUseCase<I, Flow<O>>> coMockByFlow(flow: Flow<O>): U {
    return coMockByValue(flow)
}
