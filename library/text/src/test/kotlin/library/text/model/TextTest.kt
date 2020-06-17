package library.text.model

import io.kotlintest.matchers.beInstanceOf
import io.kotlintest.should
import io.kotlintest.shouldBe
import org.junit.Test

internal class TextTest {

    @Test
    fun `text created from string should be of type chars`() {
        Text("any string") should beInstanceOf<Text.Chars>()
    }

    @Test
    fun `text created from resource ID should be of type resource`() {
        Text(42) should beInstanceOf<Text.Resource>()
    }

    @Test
    fun `string as text should be text created from given string`() {
        "any string".asText() shouldBe Text("any string")
    }

    @Test
    fun `string as text should be of type chars`() {
        "any string".asText() should beInstanceOf<Text.Chars>()
    }
}
