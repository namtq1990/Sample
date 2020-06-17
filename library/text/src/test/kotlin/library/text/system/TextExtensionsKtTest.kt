package library.text.system

import android.content.res.Resources
import io.kotlintest.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test
import library.text.model.Text

internal class TextExtensionsKtTest {

    @Test
    fun `translate of text created from string should return given string`() {
        resources().translate(Text("any string")) shouldBe "any string"
    }

    @Test
    fun `translate of text created from resource ID should call resources with given resource ID`() {
        val resources = resources()
        resources.translate(Text(42))

        verify { resources.getText(42) }
    }

    @Test
    fun `translate of text created from resource ID should return string from resources`() {
        resources().translate(Text(42)) shouldBe stringFromResources
    }

    private val stringFromResources = "string from resources"
    private fun resources(): Resources = mockk {
        every { getText(any()) } returns stringFromResources
    }
}
