package library.text.infrastructure

import io.kotlintest.shouldBe
import junitparams.JUnitParamsRunner
import junitparams.Parameters
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(JUnitParamsRunner::class)
internal class StringExtensionsKtTest {

    @Test
    fun `empty to null should return null for null string`() {
        null.emptyToNull() shouldBe null
    }

    @Test
    fun `empty to null should return null for empty string`() {
        "".emptyToNull() shouldBe null
    }

    @Test
    fun `empty to null should return original string for blank string`() {
        " ".emptyToNull() shouldBe " "
    }

    @Test
    @Parameters("a", "foo", "foo bar")
    fun `empty to null should return original string for non-empty string`(string: String) {
        string.emptyToNull() shouldBe string
    }

    @Test
    fun `join if not empty should return null for empty list`() {
        emptyList<String>().joinIfNotEmpty() shouldBe null
    }

    @Test
    fun `join if not empty should return given item for list with single item`() {
        listOf("foo").joinIfNotEmpty() shouldBe "foo"
    }

    @Test
    fun `join if not empty should return joined items for list with multiple items with  default separator`() {
        listOf("foo", "bar").joinIfNotEmpty() shouldBe "foo, bar"
    }

    @Test
    fun `join if not empty should return joined items for list with multiple items with custom separator`() {
        listOf("foo", "bar").joinIfNotEmpty(" + ") shouldBe "foo + bar"
    }

    @Test
    @Parameters(method = "remove whitespaces params")
    fun `remove whitespaces should remove all whitespaces from original string`(
        original: String,
        result: String
    ) {
        original.removeWhitespaces() shouldBe result
    }

    @Suppress("unused") private fun `remove whitespaces params`(): Any {
        return arrayOf(
            arrayOf(" a", "a"),
            arrayOf("a ", "a"),
            arrayOf(" a ", "a"),
            arrayOf("   a", "a"),
            arrayOf("a   ", "a"),
            arrayOf("   a   ", "a"),
            arrayOf("a b", "ab"),
            arrayOf("a b ", "ab"),
            arrayOf(" a b", "ab"),
            arrayOf(" a b ", "ab"),
            arrayOf(" a   b ", "ab"),
            arrayOf("   a     b     c   ", "abc"),
            arrayOf("\u0020a", "a"),
            arrayOf("a\u0020", "a"),
            arrayOf("\u0020a\u0020", "a"),
            arrayOf("\u0020\u0020\u0020a", "a"),
            arrayOf("a\u0020\u0020\u0020", "a"),
            arrayOf("\u0020\u0020\u0020a\u0020\u0020\u0020", "a"),
            arrayOf("a\u0020b", "ab"),
            arrayOf("a\u0020b\u0020", "ab"),
            arrayOf("\u0020a\u0020b", "ab"),
            arrayOf("\u0020a\u0020b\u0020", "ab"),
            arrayOf("\u0020a\u0020\u0020\u0020b\u0020", "ab"),
            arrayOf("\ta", "a"),
            arrayOf("a\t", "a"),
            arrayOf("\ta\t", "a"),
            arrayOf("\t\t\ta", "a"),
            arrayOf("a\t\t\t", "a"),
            arrayOf("\t\t\ta\t\t\t", "a"),
            arrayOf("a\tb", "ab"),
            arrayOf("a\tb\t", "ab"),
            arrayOf("\ta\tb", "ab"),
            arrayOf("\ta\tb\t", "ab"),
            arrayOf("\ta\t\t\tb\t", "ab"),
            arrayOf("\na", "a"),
            arrayOf("a\n", "a"),
            arrayOf("\na\n", "a"),
            arrayOf("\n\n\na", "a"),
            arrayOf("a\n\n\n", "a"),
            arrayOf("\n\n\na\n\n\n", "a"),
            arrayOf("a\nb", "ab"),
            arrayOf("a\nb\n", "ab"),
            arrayOf("\na\nb", "ab"),
            arrayOf("\na\nb\n", "ab"),
            arrayOf("\na\n\n\nb\n", "ab")
        )
    }
}
