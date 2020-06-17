package library.text.system

import android.content.res.Resources
import library.text.model.Text

fun Resources.translate(text: Text): CharSequence {
    return when (text) {
        is Text.Resource -> getText(text.resId)
        is Text.Chars -> text.chars
    }
}
