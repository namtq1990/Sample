package library.coroutines.domain

import io.kotlintest.shouldBe
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.CoroutineScope
import org.junit.Test

internal class CoroutineScopeUseCaseTest {

    @Test
    fun `use case with input parameter can be invoked with direct call`() {
        val doSomething = object : CoroutineScopeUseCase<Int, String> {
            override fun invoke(coroutineScope: CoroutineScope, input: Int): String = "hello"
        }

        val result = doSomething(coroutineScope(), 42)

        result shouldBe "hello"
    }

    @Test
    fun `use case without input parameter can be invoked with direct call`() {
        val doSomething = object : CoroutineScopeUseCase<Unit, String> {
            override fun invoke(coroutineScope: CoroutineScope, input: Unit): String = "hello"
        }

        val result = doSomething(coroutineScope())

        result shouldBe "hello"
    }

    @Test
    fun `use case without input parameter is invoked on given coroutine scope`() {
        val coroutineScope = coroutineScope()
        val doSomething: CoroutineScopeUseCase<Unit, Unit> = mockk(relaxed = true)

        doSomething(coroutineScope)

        verify { doSomething(coroutineScope, Unit) }
    }

    private fun coroutineScope(): CoroutineScope = mockk()
}
