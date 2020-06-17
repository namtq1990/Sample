package library.architecture.domain

import io.kotlintest.shouldBe
import org.junit.Test

internal class UseCaseTest {

    @Test
    fun `use case with input parameter can be invoked with direct call`() {
        val doSomething = object : UseCase<Int, String> {
            override fun invoke(input: Int): String = "hello"
        }

        val result = doSomething(42)

        result shouldBe "hello"
    }

    @Test
    fun `use case without input parameter can be invoked with direct call`() {
        val doSomething = object : UseCase<Unit, String> {
            override fun invoke(input: Unit): String = "hello"
        }

        val result = doSomething()

        result shouldBe "hello"
    }
}
