package library.coroutines.infrastructure

import io.kotlintest.matchers.haveSize
import io.kotlintest.should
import io.kotlintest.shouldBe
import kotlinx.coroutines.flow.flowOf
import org.junit.Test
import library.coroutines.test.infrastructure.collectValues
import library.coroutines.test.infrastructure.lastValue

internal class FlowExtensionsKtTest {

    @Test
    fun `should pair current element with previous or null for the first element`() {
        flowOf(1, 2, 3)
            .pairWithPrevious()
            .collectValues()
            .let { it shouldBe listOf(null to 1, 1 to 2, 2 to 3) }
    }

    @Test
    fun `ticker should emit in initial time`() {
        ticker(initialDelayMillis = 0, delayMillis = 1000)
            .collectValues()
            .let { it should haveSize(1) }
    }

    @Test
    fun `ticker should emit in initial time and after each delay`() {
        ticker(initialDelayMillis = 42, delayMillis = 100)
            .collectValues { advanceTimeBy(242) }
            .let { it should haveSize(3) }
    }

    @Test
    fun `ticker should emit in initial time equal to delay if not set`() {
        ticker(delayMillis = 100)
            .collectValues { advanceTimeBy(300) }
            .let { it should haveSize(3) }
    }

    @Test
    fun `combine 6 should transform all originals flows`() {
        val flow1 = flowOf(1)
        val flow2 = flowOf(2)
        val flow3 = flowOf(3)
        val flow4 = flowOf(4)
        val flow5 = flowOf(5)
        val flow6 = flowOf(6)

        combine(flow1, flow2, flow3, flow4, flow5, flow6) { t1, t2, t3, t4, t5, t6 ->
            listOf(t1, t2, t3, t4, t5, t6)
        }.lastValue() shouldBe listOf(1, 2, 3, 4, 5, 6)
    }

    @Test
    fun `combine 7 should transform all originals flows`() {
        val flow1 = flowOf(1)
        val flow2 = flowOf(2)
        val flow3 = flowOf(3)
        val flow4 = flowOf(4)
        val flow5 = flowOf(5)
        val flow6 = flowOf(6)
        val flow7 = flowOf(7)

        combine(flow1, flow2, flow3, flow4, flow5, flow6, flow7) { t1, t2, t3, t4, t5, t6, t7 ->
            listOf(t1, t2, t3, t4, t5, t6, t7)
        }.lastValue() shouldBe listOf(1, 2, 3, 4, 5, 6, 7)
    }

    @Test
    fun `combine 8 should transform all originals flows`() {
        val flow1 = flowOf(1)
        val flow2 = flowOf(2)
        val flow3 = flowOf(3)
        val flow4 = flowOf(4)
        val flow5 = flowOf(5)
        val flow6 = flowOf(6)
        val flow7 = flowOf(7)
        val flow8 = flowOf(8)

        combine(
            flow1, flow2, flow3, flow4, flow5, flow6, flow7, flow8
        ) { t1, t2, t3, t4, t5, t6, t7, t8 ->
            listOf(t1, t2, t3, t4, t5, t6, t7, t8)
        }.lastValue() shouldBe listOf(1, 2, 3, 4, 5, 6, 7, 8)
    }

    @Test
    fun `combine 9 should transform all originals flows`() {
        val flow1 = flowOf(1)
        val flow2 = flowOf(2)
        val flow3 = flowOf(3)
        val flow4 = flowOf(4)
        val flow5 = flowOf(5)
        val flow6 = flowOf(6)
        val flow7 = flowOf(7)
        val flow8 = flowOf(8)
        val flow9 = flowOf(9)

        combine(
            flow1, flow2, flow3, flow4, flow5, flow6, flow7, flow8, flow9
        ) { t1, t2, t3, t4, t5, t6, t7, t8, t9 ->
            listOf(t1, t2, t3, t4, t5, t6, t7, t8, t9)
        }.lastValue() shouldBe listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
    }
}
