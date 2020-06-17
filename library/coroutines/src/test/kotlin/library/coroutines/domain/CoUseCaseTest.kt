package library.coroutines.domain

import io.kotlintest.shouldBe
import kotlinx.coroutines.runBlocking
import org.junit.Test

internal class CoUseCaseTest {

    @Test
    fun `use case with input parameter can be invoked with direct call`() {
        val doSomething = object : CoUseCase<Int, String> {
            override suspend fun invoke(input: Int): String = "hello"
        }

        val result = runBlocking { doSomething(42) }

        result shouldBe "hello"
    }

    @Test
    fun `use case without input parameter can be invoked with direct call`() {
        val doSomething = object : CoUseCase<Unit, String> {
            override suspend fun invoke(input: Unit): String = "hello"
        }

        val result = runBlocking { doSomething() }

        result shouldBe "hello"
    }
}
