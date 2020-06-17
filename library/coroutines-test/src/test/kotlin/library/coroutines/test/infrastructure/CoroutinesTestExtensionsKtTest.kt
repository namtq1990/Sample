package library.coroutines.test.infrastructure

import io.kotlintest.shouldBe
import io.kotlintest.shouldThrowAny
import kotlinx.coroutines.flow.asFlow
import org.junit.Test

internal class CoroutinesTestExtensionsKtTest {

    @Test
    fun `lastValue of flow should return last value emitted`() {
        listOf(1, 2, 3).asFlow().lastValue() shouldBe 3
    }

    @Test
    fun `lastValue of flow should throw exception when flow is empty`() {
        shouldThrowAny {
            emptyList<Any>().asFlow().lastValue()
        }
    }
}
