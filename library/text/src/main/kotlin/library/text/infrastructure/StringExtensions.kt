package library.text.infrastructure

fun String?.emptyToNull() = this?.takeUnless { it.isEmpty() }

fun List<String>.joinIfNotEmpty(separator: CharSequence = ", "): String? {
    return takeIf { it.isNotEmpty() }?.joinToString(separator = separator)
}

fun String.removeWhitespaces() = replace(Regex("\\s+"), "")
