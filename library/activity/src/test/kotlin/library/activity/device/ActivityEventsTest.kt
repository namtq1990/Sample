package library.activity.device

import io.kotlintest.matchers.haveSize
import io.kotlintest.should
import io.kotlintest.shouldBe
import io.mockk.mockk
import org.junit.Test
import library.activity.device.ActivityEvent.ActivityCreate
import library.activity.device.ActivityEvent.ActivityDestroy
import library.coroutines.test.infrastructure.collectValues

internal class ActivityEventsTest {

    private val events = ActivityEvents()

    @Test
    fun `get() should emit nothing by default`() {
        events.get()
            .collectValues()
            .let { values ->
                values shouldBe emptyList()
            }
    }

    @Test
    fun `get() should emit all posted events`() {
        events
            .get()
            .collectValues {
                events.post(ActivityCreate(mockk()))
                events.post(ActivityDestroy(mockk()))
                events.post(ActivityCreate(mockk()))
                events.post(ActivityDestroy(mockk()))
            }
            .let { values ->
                values should haveSize(4)
            }
    }

    @Test
    fun `get() should emit events only when subscribed`() {
        events.post(ActivityCreate(mockk()))
        events
            .get()
            .collectValues {
                events.post(ActivityCreate(mockk()))
            }
            .let { values ->
                values should haveSize(1)
            }
    }
}
