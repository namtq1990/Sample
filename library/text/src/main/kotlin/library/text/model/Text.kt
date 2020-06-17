package library.text.model

sealed class Text {

    internal data class Resource(val resId: Int) : Text()
    internal data class Chars(val chars: CharSequence) : Text()

    companion object {
        operator fun invoke(resId: Int): Text = Resource(resId)
        operator fun invoke(chars: CharSequence): Text = Chars(chars)
    }
}

fun CharSequence.asText() = Text(this)
