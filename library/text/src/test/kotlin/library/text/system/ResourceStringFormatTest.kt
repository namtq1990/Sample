package library.text.system

import android.content.res.Resources
import io.kotlintest.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

internal class ResourceStringFormatTest {

    @Test
    fun `get string should delegate to resources`() {
        val resources = resources()

        ResourceStringFormat(resources).getString(42) shouldBe stringFromRes

        verify {
            resources.getString(42)
        }
    }

    @Test
    fun `get string with arguments should delegate to resources`() {
        val resources = resources()

        ResourceStringFormat(resources).getString(42, "a1", "a2") shouldBe stringFromResArgs

        verify {
            resources.getString(42, "a1", "a2")
        }
    }

    @Test
    fun `get quantity string should delegate to resources`() {
        val resources = resources()

        ResourceStringFormat(resources).getQuantityString(42, 24) shouldBe quantityStringFromRes

        verify {
            resources.getQuantityString(42, 24)
        }
    }

    @Test
    fun `get quantity string with arguments should delegate to resources`() {
        val resources = resources()

        ResourceStringFormat(resources)
            .getQuantityString(42, 24, "a1", "a2") shouldBe quantityStringFromResArgs

        verify {
            resources.getQuantityString(42, 24, "a1", "a2")
        }
    }

    private val stringFromRes = "string from resources"
    private val stringFromResArgs = "string from resources with args"
    private val quantityStringFromRes = "quantity string from resources"
    private val quantityStringFromResArgs = "quantity string from resources with args"
    private fun resources(): Resources = mockk {
        every { getString(any()) } returns stringFromRes
        every { getString(any(), any(), any()) } returns stringFromResArgs
        every { getQuantityString(any(), any()) } returns quantityStringFromRes
        every { getQuantityString(any(), any(), any(), any()) } returns quantityStringFromResArgs
    }
}
